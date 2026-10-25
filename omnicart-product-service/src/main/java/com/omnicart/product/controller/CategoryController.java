package com.omnicart.product.controller;

import com.omnicart.product.dto.request.UpdateCategoryRequestDto;
import com.omnicart.product.dto.response.CategoryResponseDto;
import com.omnicart.product.dto.request.CreateCategoryRequestDto;
import com.omnicart.product.exception.CustomBusinessException;
import com.omnicart.product.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ranjit Soni on 19-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @PreAuthorize("hasAuthority('CREATED_CATEGORY')")
    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@Validated @RequestBody CreateCategoryRequestDto categoryRequestDto) throws CustomBusinessException {
        CategoryResponseDto categoryResponseDto = categoryService.createCategory(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponseDto);
    }

    @PreAuthorize("hasAuthority('UPDATE_CATEGORY')")
    @PutMapping
    public ResponseEntity<CategoryResponseDto> updateCategory(@Validated @RequestBody UpdateCategoryRequestDto categoryRequestDto) throws CustomBusinessException {
        CategoryResponseDto categoryResponseDto = categoryService.updatedCategory(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDto);
    }

    @PreAuthorize("hasAuthority('READ_CATEGORY')")
    @GetMapping("/{pageNumber}/{pageSize}")
    public ResponseEntity<List<CategoryResponseDto>> getCategories(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        List<CategoryResponseDto> categoryResponseDtoList = categoryService.getCategories(pageNumber, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDtoList);
    }

    @PreAuthorize("hasAuthority('READ_CATEGORY')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable("id") long categoryId) throws CustomBusinessException {
        CategoryResponseDto categoryResponseDto = categoryService.getCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDto);
    }

    @PreAuthorize("hasAuthority('DELETE_CATEGORY')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> deleteCategoryById(@PathVariable("id") long categoryId) throws CustomBusinessException {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().build();
    }
}
