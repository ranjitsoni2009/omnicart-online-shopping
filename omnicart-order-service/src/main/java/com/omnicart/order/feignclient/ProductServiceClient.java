package com.omnicart.order.feignclient;

import com.omnicart.order.config.JwtFeignConfiguration;
import com.omnicart.order.dto.request.ReserveInventoryQuantityRequestDto;
import com.omnicart.order.feignclient.response.InventoryResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "omnicart-product-service", configuration = JwtFeignConfiguration.class)
public interface ProductServiceClient {

    @GetMapping("/api/inventories")
    InventoryResponse getInventory(@RequestParam("productVariantId") Long productVariantId, @RequestParam("vendorId") Long vendorId);

    @PutMapping("/api/inventories/reserve")
    List<InventoryResponse> reserveInventory(@NotNull @NotEmpty @RequestBody List<ReserveInventoryQuantityRequestDto> reserveInventoryRequests);

}
