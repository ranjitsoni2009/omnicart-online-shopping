package com.omnicart.product.controller;

import com.omnicart.product.dto.request.CreateProductVariantRequestDto;
import com.omnicart.product.dto.request.UpdateProductVariantRequestDto;
import com.omnicart.product.dto.response.ProductVariantResponseDto;
import com.omnicart.product.exception.CustomBusinessException;
import com.omnicart.product.service.IProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ranjit Soni on 26-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@RestController
@RequestMapping("/api/product-variants")
@RequiredArgsConstructor
public class ProductVariantController {

    private final IProductVariantService productVariantService;

    @PostMapping
    public ResponseEntity<ProductVariantResponseDto> createProductVariant(@RequestBody CreateProductVariantRequestDto variantRequestDto) throws CustomBusinessException {
        ProductVariantResponseDto productVariantResponseDto = productVariantService.createProductVariant(variantRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productVariantResponseDto);
    }

    @PutMapping
    public ResponseEntity<ProductVariantResponseDto> updateProductVariant(@RequestBody UpdateProductVariantRequestDto variantRequestDto) throws CustomBusinessException {
        ProductVariantResponseDto productVariantResponseDto = productVariantService.updateProductVariant(variantRequestDto);
        return ResponseEntity.ok(productVariantResponseDto);
    }

    @GetMapping("/{productId}/{pageNumber}/{pageSize}")
    public ResponseEntity<List<ProductVariantResponseDto>> getProductVariants(
            @PathVariable("productId") long productId, @PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        List<ProductVariantResponseDto> productVariantResponseDtoList = productVariantService.getProductVariants(productId, pageNumber, pageSize);
        return ResponseEntity.ok(productVariantResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductVariantResponseDto> getProductVariant(@PathVariable("id") long productVariantId) throws CustomBusinessException {
        ProductVariantResponseDto productVariantResponseDto = productVariantService.getProductVariant(productVariantId);
        return ResponseEntity.ok(productVariantResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductVariant(@PathVariable("id") long productVariantId) throws CustomBusinessException {
        productVariantService.deleteProductVariant(productVariantId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
