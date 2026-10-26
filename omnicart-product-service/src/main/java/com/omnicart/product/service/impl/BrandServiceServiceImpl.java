package com.omnicart.product.service.impl;

import com.omnicart.product.dto.request.CreateBrandRequestDto;
import com.omnicart.product.dto.request.UpdateBrandRequestDto;
import com.omnicart.product.dto.response.BrandResponseDto;
import com.omnicart.product.entity.Brand;
import com.omnicart.product.exception.CustomBusinessException;
import com.omnicart.product.repo.IBrandRepo;
import com.omnicart.product.service.IBrandService;
import com.omnicart.product.mapper.GenericMapper;
import com.omnicart.product.util.OmniCartProductConstant;
import com.omnicart.product.util.OmniCartProductUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Service
@AllArgsConstructor
public class BrandServiceServiceImpl implements IBrandService {

    private IBrandRepo brandRepo;
    private GenericMapper genericMapper;

    @Override
    public BrandResponseDto createBrand(CreateBrandRequestDto createBrandRequestDto) throws CustomBusinessException {

        Optional<Brand> optionalBrand = brandRepo.findByName(createBrandRequestDto.getName());
        if (optionalBrand.isPresent()) {
            throw new CustomBusinessException(
                    OmniCartProductConstant.BRAND_ALREADY_EXIST_ERROR_MSG,
                    OmniCartProductUtil.getErrorCode(
                            OmniCartProductConstant.BUSINESS_EXCEPTION_TAG, OmniCartProductConstant.BRAND_ALREADY_EXIST_ERROR_CODE));
        }

        Brand brand = genericMapper.toEntity(createBrandRequestDto, Brand.class);
        brand.setActive(true);
        brand.setCreatedAt(Instant.now());
        brand.setUpdatedAt(Instant.now());
        brand = brandRepo.save(brand);
        return genericMapper.toDto(brand, BrandResponseDto.class);
    }

    @Override
    public BrandResponseDto updateBrand(UpdateBrandRequestDto updateBrandRequestDto) throws CustomBusinessException {
        Brand brand = getBrandById(updateBrandRequestDto.getId());
        brand = genericMapper.toEntity(updateBrandRequestDto, brand, "id");
        brand.setUpdatedAt(Instant.now());
        brand.setActive(true);
        brand = brandRepo.save(brand);
        return genericMapper.toDto(brand, BrandResponseDto.class);
    }

    @Override
    public List<BrandResponseDto> getBrands(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Brand> page = brandRepo.findAllByIsActive(true, pageable);
        List<Brand> brands = page.getContent();
        return brands.stream()
                .map(brand ->
                        genericMapper.toDto(brand, BrandResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BrandResponseDto getBrand(long brandId) throws CustomBusinessException {
        Brand brand = getBrandById(brandId);
        return genericMapper.toDto(brand, BrandResponseDto.class);
    }

    @Override
    public void deleteBrand(long brandId) throws CustomBusinessException {
        Brand brand = getBrandById(brandId);
        brand.setActive(false);
        brandRepo.save(brand);
    }

    @Override
    public Brand getBrandById(long brandId) throws CustomBusinessException {
        return brandRepo.findById(brandId)
                .orElseThrow(() ->
                        new CustomBusinessException(
                                OmniCartProductConstant.BRAND_NOT_FOUND,
                                OmniCartProductUtil.getErrorCode(
                                        OmniCartProductConstant.BUSINESS_EXCEPTION_TAG, OmniCartProductConstant.BRAND_NOT_FOUND_ERROR_CODE)));
    }
}
