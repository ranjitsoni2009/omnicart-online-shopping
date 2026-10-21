package com.omnicart.product.service.impl;

import com.omnicart.product.dto.response.CategoryResponseDto;
import com.omnicart.product.dto.request.CreateCategoryRequestDto;
import com.omnicart.product.dto.request.UpdateCategoryRequestDto;
import com.omnicart.product.service.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Service
public class CategoryServiceServiceImpl implements ICategoryService {

    @Override
    public CategoryResponseDto createCategory(CreateCategoryRequestDto categoryRequestDto) {
        return null;
    }

    @Override
    public List<CategoryResponseDto> getCategories(int pageNumber, int pageSize) {
        return List.of();
    }

    @Override
    public CategoryResponseDto getCategory(Long categoryId) {
        return null;
    }

    @Override
    public CategoryResponseDto updatedCategory(UpdateCategoryRequestDto updateCategoryRequestDto) {
        return null;
    }

    @Override
    public void deleteCategory(Long categoryId) {

    }
}
