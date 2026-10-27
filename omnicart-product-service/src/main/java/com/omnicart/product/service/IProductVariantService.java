package com.omnicart.product.service;


import com.omnicart.product.dto.request.CreateProductVariantRequestDto;
import com.omnicart.product.dto.request.UpdateProductVariantRequestDto;
import com.omnicart.product.dto.response.ProductVariantResponseDto;
import com.omnicart.product.exception.CustomBusinessException;

import java.util.List;

public interface IProductVariantService {

    ProductVariantResponseDto createProductVariant(CreateProductVariantRequestDto createProductVariantRequestDto) throws CustomBusinessException;

    ProductVariantResponseDto updateProductVariant(UpdateProductVariantRequestDto updateProductVariantRequestDto) throws CustomBusinessException;

    List<ProductVariantResponseDto> getProductVariants(long productId, int pageNumber, int pageSize);

    ProductVariantResponseDto getProductVariant(long ProductVariantId) throws CustomBusinessException;

    void deleteProductVariant(long ProductVariantId) throws CustomBusinessException;
}
