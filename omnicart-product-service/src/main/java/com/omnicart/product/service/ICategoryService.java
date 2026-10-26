package com.omnicart.product.service;

import com.omnicart.product.dto.response.CategoryResponseDto;
import com.omnicart.product.dto.request.CreateCategoryRequestDto;
import com.omnicart.product.dto.request.UpdateCategoryRequestDto;
import com.omnicart.product.entity.Category;
import com.omnicart.product.exception.CustomBusinessException;

import java.util.List;

public interface ICategoryService {

    CategoryResponseDto createCategory(CreateCategoryRequestDto categoryRequestDto) throws CustomBusinessException;

    List<CategoryResponseDto> getCategories(int pageNumber, int pageSize);

    CategoryResponseDto getCategory(Long categoryId) throws CustomBusinessException;

    CategoryResponseDto updatedCategory(UpdateCategoryRequestDto updateCategoryRequestDto) throws CustomBusinessException;

    void deleteCategory(Long categoryId) throws CustomBusinessException;

    Category getCategoryById(Long categoryId) throws CustomBusinessException;
}
