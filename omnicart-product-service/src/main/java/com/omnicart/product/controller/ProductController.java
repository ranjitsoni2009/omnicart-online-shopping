package com.omnicart.product.controller;

import com.omnicart.product.dto.request.CreateProductRequestDto;
import com.omnicart.product.dto.request.UpdateProductRequestDto;
import com.omnicart.product.dto.response.ProductResponseDto;
import com.omnicart.product.entity.Product;
import com.omnicart.product.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        return null;
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody UpdateProductRequestDto updateProductRequestDto) {
        return null;
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public ResponseEntity<List<ProductResponseDto>> getProducts(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable("id") int id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable("id") int id) {
        return null;
    }
}
