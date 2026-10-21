package com.omnicart.product.service;

import com.omnicart.product.dto.response.CategoryResponseDto;
import com.omnicart.product.dto.request.CreateCategoryRequestDto;
import com.omnicart.product.dto.request.UpdateCategoryRequestDto;

import java.util.List;

public interface ICategoryService {

    CategoryResponseDto createCategory(CreateCategoryRequestDto categoryRequestDto);

    List<CategoryResponseDto> getCategories(int pageNumber, int pageSize);

    CategoryResponseDto getCategory(Long categoryId);

    CategoryResponseDto updatedCategory(UpdateCategoryRequestDto updateCategoryRequestDto);

    void deleteCategory(Long categoryId);
}
