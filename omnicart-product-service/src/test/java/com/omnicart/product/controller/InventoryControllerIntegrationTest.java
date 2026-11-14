package com.omnicart.product.controller;

import com.omnicart.product.entity.ProductVariant;
import com.omnicart.product.repo.IInventoryRepo;
import com.omnicart.product.repo.IProductVariantRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class InventoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IInventoryRepo inventoryRepo;
    @Autowired
    private IProductVariantRepo productVariantRepo;

    private long productVariantId;

    @BeforeEach
    void setUp() {
        inventoryRepo.deleteAll();
        productVariantRepo.deleteAll();
        ProductVariant productVariant = new ProductVariant();
        productVariant.setSkuId("SKU-TEST-1");
        productVariant.setActive(true);
        productVariantId = productVariantRepo.save(productVariant).getId();
    }

    @Test
    void authorizedAdministratorCanCreateAndReadTheirInventory() throws Exception {
        String request = """
                {"productVariantId": %d, "vendorId": 901, "quantity": 12}
                """.formatted(productVariantId);

        mockMvc.perform(post("/api/inventories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .with(jwt().jwt(jwt -> jwt.subject("501")).authorities(
                                new SimpleGrantedAuthority("CREATE_INVENTORY"),
                                new SimpleGrantedAuthority("READ_INVENTORY"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vendorId").value(901))
                .andExpect(jsonPath("$.quantity").value(12))
                .andExpect(jsonPath("$.availableQuantity").value(12));

        mockMvc.perform(get("/api/inventories?pageNumber=0&pageSize=10")
                        .with(jwt().jwt(jwt -> jwt.subject("501")).authorities(
                                new SimpleGrantedAuthority("READ_INVENTORY"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].vendorId").value(901));
    }

    @Test
    void createRequiresCreateInventoryAuthority() throws Exception {
        String request = """
                {"productVariantId": %d, "vendorId": 901, "quantity": 12}
                """.formatted(productVariantId);

        mockMvc.perform(post("/api/inventories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .with(jwt().jwt(jwt -> jwt.subject("501")).authorities(
                                new SimpleGrantedAuthority("READ_INVENTORY"))))
                .andExpect(status().isForbidden());
    }
}
