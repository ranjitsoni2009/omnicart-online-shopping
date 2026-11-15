package com.omnicart.order.feignclient;

import com.omnicart.order.feignclient.response.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "omnicart-product-service")
public interface ProductServiceClient {

    @GetMapping("/api/inventories")
    InventoryResponse getInventory(@RequestParam("productVariantId") Long productVariantId, @RequestParam("vendorId") Long vendorId);

}
