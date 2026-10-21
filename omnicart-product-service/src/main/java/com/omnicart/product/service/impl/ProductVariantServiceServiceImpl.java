package com.omnicart.product.service.impl;

import com.omnicart.product.dto.request.CreateProductVariantRequestDto;
import com.omnicart.product.dto.request.UpdateProductVariantRequestDto;
import com.omnicart.product.dto.response.ProductVariantResponseDto;
import com.omnicart.product.service.IProductVariantService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Service
public class ProductVariantServiceServiceImpl implements IProductVariantService {

    @Override
    public ProductVariantResponseDto createProductVariant(CreateProductVariantRequestDto createProductVariantRequestDto) {
        return null;
    }

    @Override
    public ProductVariantResponseDto updateProductVariant(UpdateProductVariantRequestDto updateProductVariantRequestDto) {
        return null;
    }

    @Override
    public List<ProductVariantResponseDto> getProductVariants(int pageNumber, int pageSize) {
        return List.of();
    }

    @Override
    public ProductVariantResponseDto getProductVariant(long ProductVariantId) {
        return null;
    }

    @Override
    public void deleteProductVariant(long ProductVariantId) {

    }
}
