package com.omnicart.product.service.impl;

import com.omnicart.product.dto.request.CreateInventoryRequestDto;
import com.omnicart.product.dto.request.UpdateInventoryRequestDto;
import com.omnicart.product.entity.Inventory;
import com.omnicart.product.entity.ProductVariant;
import com.omnicart.product.exception.CustomBusinessException;
import com.omnicart.product.repo.IInventoryRepo;
import com.omnicart.product.repo.IProductVariantRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {

    @Mock
    private IInventoryRepo inventoryRepo;
    @Mock
    private IProductVariantRepo productVariantRepo;
    @InjectMocks
    private InventoryServiceImpl inventoryService;
    @Captor
    private ArgumentCaptor<Inventory> inventoryCaptor;

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void createInventoryUsesAuthenticatedAdministratorAndPersistsVendorAssociation() throws Exception {
        authenticateAs(101);
        ProductVariant productVariant = productVariant(9);
        when(inventoryRepo.findByProductVariantIdAndVendorId(9, 88)).thenReturn(Optional.empty());
        when(productVariantRepo.findById(9L)).thenReturn(Optional.of(productVariant));
        when(inventoryRepo.save(any(Inventory.class))).thenAnswer(invocation -> {
            Inventory inventory = invocation.getArgument(0);
            inventory.setId(3);
            return inventory;
        });

        var response = inventoryService.createInventory(createRequest(9, 88, 15));

        verify(inventoryRepo).save(inventoryCaptor.capture());
        Inventory saved = inventoryCaptor.getValue();
        assertThat(saved.getAdminId()).isEqualTo(101);
        assertThat(saved.getVendorId()).isEqualTo(88);
        assertThat(saved.getProductVariant()).isSameAs(productVariant);
        assertThat(response.getAvailableQuantity()).isEqualTo(15);
    }

    @Test
    void updateInventoryRejectsQuantityBelowReservedStock() {
        authenticateAs(101);
        Inventory inventory = inventory(3, 101, 88, 10, 6);
        when(inventoryRepo.findById(3L)).thenReturn(Optional.of(inventory));

        assertThatThrownBy(() -> inventoryService.updateInventory(3, updateRequest(5)))
                .isInstanceOf(CustomBusinessException.class)
                .hasMessage("Inventory quantity cannot be less than reserved quantity");
    }

    @Test
    void getInventoryRejectsAnotherAdministratorsInventory() {
        authenticateAs(101);
        when(inventoryRepo.findById(anyLong())).thenReturn(Optional.of(inventory(3, 202, 88, 10, 0)));

        assertThatThrownBy(() -> inventoryService.getInventory(3))
                .isInstanceOf(CustomBusinessException.class)
                .hasMessage("You are not allowed to access this inventory");
    }

    private void authenticateAs(long adminId) {
        TestingAuthenticationToken authentication = new TestingAuthenticationToken(String.valueOf(adminId), null);
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private CreateInventoryRequestDto createRequest(long variantId, long vendorId, long quantity) {
        CreateInventoryRequestDto request = new CreateInventoryRequestDto();
        request.setProductVariantId(variantId);
        request.setVendorId(vendorId);
        request.setQuantity(quantity);
        return request;
    }

    private UpdateInventoryRequestDto updateRequest(long quantity) {
        UpdateInventoryRequestDto request = new UpdateInventoryRequestDto();
        request.setQuantity(quantity);
        return request;
    }

    private ProductVariant productVariant(long id) {
        ProductVariant productVariant = new ProductVariant();
        productVariant.setId(id);
        return productVariant;
    }

    private Inventory inventory(long id, long adminId, long vendorId, long quantity, long reservedQuantity) {
        Inventory inventory = new Inventory();
        inventory.setId(id);
        inventory.setAdminId(adminId);
        inventory.setVendorId(vendorId);
        inventory.setQuantity(quantity);
        inventory.setReservedQuantity(reservedQuantity);
        inventory.setActive(true);
        inventory.setProductVariant(productVariant(9));
        return inventory;
    }
}
