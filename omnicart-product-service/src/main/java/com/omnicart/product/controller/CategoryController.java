package com.omnicart.product.controller;

import com.omnicart.product.dto.response.CategoryResponseDto;
import com.omnicart.product.dto.request.CreateCategoryRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ranjit Soni on 19-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @PreAuthorize("hasAuthority('CREATED_CATEGORY')")
    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@Validated @RequestBody CreateCategoryRequestDto categoryRequestDto) {
        return  null;
    }

    @PreAuthorize("hasAuthority('UPDATE_CATEGORY')")
    @PutMapping
    public ResponseEntity<CategoryResponseDto> updateCategory(@Validated @RequestBody CreateCategoryRequestDto categoryRequestDto) {
        return  null;
    }

    @PreAuthorize("hasAuthority('READ_CATEGORY')")
    @GetMapping
    public ResponseEntity<CategoryResponseDto> getCategories(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        return  null;
    }

    @PreAuthorize("hasAuthority('READ_CATEGORY')")
    @GetMapping
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable("id") Long categoryId) {
        return  null;
    }

    @PreAuthorize("hasAuthority('DELETE_CATEGORY')")
    @DeleteMapping
    public ResponseEntity<CategoryResponseDto> deleteCategoryById(@PathVariable("id") Long categoryId) {
        return  null;
    }
}
