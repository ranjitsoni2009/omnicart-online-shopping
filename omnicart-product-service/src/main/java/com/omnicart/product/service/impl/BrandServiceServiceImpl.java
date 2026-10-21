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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        Optional<Brand> optionalBrand = brandRepo.findByName(createBrandRequestDto.getBrandName());
        if (optionalBrand.isPresent()) {
            throw new CustomBusinessException(
                    OmniCartProductConstant.BRAND_ALREADY_EXIST_ERROR_MSG,
                    OmniCartProductUtil.getErrorCode(
                            OmniCartProductConstant.BUSINESS_EXCEPTION_TAG, OmniCartProductConstant.BRAND_ALREADY_EXIST_ERROR_CODE));
        }

        Brand brand = genericMapper.toEntity(createBrandRequestDto, Brand.class);
        brand = brandRepo.save(brand);
        return genericMapper.toDto(brand, BrandResponseDto.class);
    }

    @Override
    public BrandResponseDto updateBrand(UpdateBrandRequestDto updateBrandRequestDto) {
        return null;
    }

    @Override
    public List<BrandResponseDto> getBrands(int pageNumber, int pageSize) {
        return List.of();
    }

    @Override
    public BrandResponseDto getBrand(long brandId) {
        return null;
    }

    @Override
    public void deleteBrand(long brandId) {

    }
}
