package com.omnicart.product.controller;

import com.omnicart.product.dto.request.CreateProductVariantRequestDto;
import com.omnicart.product.dto.request.UpdateProductVariantRequestDto;
import com.omnicart.product.dto.response.ProductVariantResponseDto;
import com.omnicart.product.service.IProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ranjit Soni on 26-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@RestController
@RequestMapping("/api/product-variant")
@RequiredArgsConstructor
public class ProductVariantController {

    private final IProductVariantService productVariantService;

    @PostMapping
    public ResponseEntity<ProductVariantResponseDto> createProductVariant(@RequestBody CreateProductVariantRequestDto variantRequestDto) {
        return null;
    }

    @PutMapping
    public ResponseEntity<ProductVariantResponseDto> updateProductVariant(@RequestBody UpdateProductVariantRequestDto variantRequestDto) {
        return null;
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public ResponseEntity<ProductVariantResponseDto> getProductVariants(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductVariantResponseDto> getProductVariant(@PathVariable("id") long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductVariantResponseDto> deleteProductVariant(@PathVariable("id") long id) {
        return null;
    }
}
