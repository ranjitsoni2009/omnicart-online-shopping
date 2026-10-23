package com.omnicart.product.service;

import com.omnicart.product.dto.request.CreateBrandRequestDto;
import com.omnicart.product.dto.request.UpdateBrandRequestDto;
import com.omnicart.product.dto.response.BrandResponseDto;
import com.omnicart.product.exception.CustomBusinessException;

import java.util.List;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
public interface IBrandService {

    BrandResponseDto createBrand(CreateBrandRequestDto createBrandRequestDto) throws CustomBusinessException;

    BrandResponseDto updateBrand(UpdateBrandRequestDto updateBrandRequestDto) throws CustomBusinessException;

    List<BrandResponseDto> getBrands(int pageNumber, int pageSize);

    BrandResponseDto getBrand(long brandId) throws CustomBusinessException;

    void deleteBrand(long brandId) throws CustomBusinessException;

}
