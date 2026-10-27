package com.omnicart.product.controller;

import com.omnicart.product.dto.request.CreateProductRequestDto;
import com.omnicart.product.dto.request.UpdateProductRequestDto;
import com.omnicart.product.dto.response.ProductResponseDto;
import com.omnicart.product.exception.CustomBusinessException;
import com.omnicart.product.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ranjit Soni on 19-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) throws CustomBusinessException {
        ProductResponseDto productResponseDto = productService.createProduct(createProductRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody UpdateProductRequestDto updateProductRequestDto) throws CustomBusinessException {
        ProductResponseDto productResponseDto = productService.updateProduct(updateProductRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public ResponseEntity<List<ProductResponseDto>> getProducts(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        List<ProductResponseDto> productResponseDtoList = productService.getProducts(pageNumber, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable("id") int id) throws CustomBusinessException {
        ProductResponseDto productResponseDto = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) throws CustomBusinessException {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
