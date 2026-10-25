package com.omnicart.product.service.impl;

import com.omnicart.product.dto.response.BrandResponseDto;
import com.omnicart.product.dto.response.CategoryResponseDto;
import com.omnicart.product.dto.request.CreateCategoryRequestDto;
import com.omnicart.product.dto.request.UpdateCategoryRequestDto;
import com.omnicart.product.entity.Brand;
import com.omnicart.product.entity.Category;
import com.omnicart.product.entity.CategoryBrand;
import com.omnicart.product.exception.CustomBusinessException;
import com.omnicart.product.mapper.GenericMapper;
import com.omnicart.product.repo.IBrandRepo;
import com.omnicart.product.repo.ICategoryBrandRepo;
import com.omnicart.product.repo.ICategoryRepo;
import com.omnicart.product.service.ICategoryService;
import com.omnicart.product.util.OmniCartProductConstant;
import com.omnicart.product.util.OmniCartProductUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceServiceImpl implements ICategoryService {

    private final ICategoryRepo categoryRepo;
    private final IBrandRepo brandRepo;
    private final ICategoryBrandRepo categoryBrandRepo;
    private final GenericMapper genericMapper;

    @Override
    public CategoryResponseDto createCategory(CreateCategoryRequestDto categoryRequestDto) throws CustomBusinessException {
        Optional<Category> optionalCategory = categoryRepo.findByName(categoryRequestDto.getName());
        if (optionalCategory.isPresent()) {
            throw new CustomBusinessException(
                    OmniCartProductConstant.CATEGORY_ALREADY_EXIST_ERROR_MSG,
                    OmniCartProductUtil.getErrorCode(
                            OmniCartProductConstant.BUSINESS_EXCEPTION_TAG, OmniCartProductConstant.CATEGORY_ALREADY_EXIST_ERROR_CODE));
        }

        Category category = genericMapper.toEntity(categoryRequestDto, Category.class);
        category.setActive(true);
        category.setCreatedAt(Instant.now());
        category.setUpdatedAt(Instant.now());
        category = categoryRepo.save(category);
        return genericMapper.toDto(category, CategoryResponseDto.class);
    }

    @Override
    public List<CategoryResponseDto> getCategories(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Category> page = categoryRepo.findByIsActive(true, pageable);
        return page.getContent().stream()
                .map(category ->
                        genericMapper.toDto(category, CategoryResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto getCategory(Long categoryId) throws CustomBusinessException {
        Category category = getCategoryById(categoryId);
        return genericMapper.toDto(category, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto updatedCategory(UpdateCategoryRequestDto updateCategoryRequestDto) throws CustomBusinessException {
        Category category = getCategoryById(updateCategoryRequestDto.getId());
        category = genericMapper.toEntity(updateCategoryRequestDto, category, "id");

        updateCategoryBrandMapping(updateCategoryRequestDto, category);

        category = categoryRepo.save(category);
        CategoryResponseDto categoryResponseDto = genericMapper.toDto(category, CategoryResponseDto.class);
        List<BrandResponseDto> brandResponseList = category.getCategoryBrands().stream().map(categoryBrand -> genericMapper.toDto(categoryBrand, BrandResponseDto.class)).toList();
        categoryResponseDto.setBrandList(brandResponseList);
        return categoryResponseDto;
    }

    @Transactional
    private void updateCategoryBrandMapping(UpdateCategoryRequestDto updateCategoryRequestDto, Category category) {
        if (!CollectionUtils.isEmpty(updateCategoryRequestDto.getBrandMap())) {
            List<CategoryBrand> categoryBrands = updateCategoryRequestDto.getBrandMap().entrySet().stream()
                    .map(entry -> {
                        if (entry.getKey() != null && entry.getValue() != null) {
                            long brandId = entry.getKey();
                            boolean isActive = entry.getValue();

                            Optional<CategoryBrand> optionalCategoryBrand = categoryBrandRepo
                                    .findByCategoryIdAndBrandId(updateCategoryRequestDto.getId(), brandId);
                            CategoryBrand categoryBrand = null;
                            if (optionalCategoryBrand.isPresent()) {
                                categoryBrand = optionalCategoryBrand.get();
                                categoryBrand.setActive(isActive);
                                categoryBrand.setUpdatedAt(Instant.now());
                            } else {
                                Optional<Brand> optionalBrand = brandRepo.findById(brandId);
                                if (optionalBrand.isEmpty()) {
                                    log.error("Brand is not available with brandId :: {}", brandId);
                                    return null;
                                }
                                categoryBrand = new CategoryBrand();
                                categoryBrand.setCategory(category);
                                categoryBrand.setBrand(optionalBrand.get());
                                categoryBrand.setActive(true);
                                categoryBrand.setCreatedAt(Instant.now());
                                categoryBrand.setUpdatedAt(Instant.now());
                            }
                            return categoryBrand;
                        }
                        return null;
                    }).toList();

            categoryBrands = categoryBrands.stream().filter(Objects::nonNull).toList();
            categoryBrandRepo.saveAll(categoryBrands);
        }
    }

    @Override
    public void deleteCategory(Long categoryId) throws CustomBusinessException {
        Category category = getCategoryById(categoryId);
        category.setActive(false);
        categoryRepo.save(category);
    }

    private Category getCategoryById(Long categoryId) throws CustomBusinessException {
        return categoryRepo.findById(categoryId)
                .orElseThrow(() ->
                        new CustomBusinessException(
                                OmniCartProductConstant.CATEGORY_NOT_FOUND,
                                OmniCartProductUtil.getErrorCode(
                                        OmniCartProductConstant.BUSINESS_EXCEPTION_TAG, OmniCartProductConstant.CATEGORY_NOT_FOUND_ERROR_CODE)));
    }
}
