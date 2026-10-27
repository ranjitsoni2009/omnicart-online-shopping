package com.omnicart.product.service;


import com.omnicart.product.dto.request.CreateProductVariantRequestDto;
import com.omnicart.product.dto.request.UpdateProductVariantRequestDto;
import com.omnicart.product.dto.response.ProductVariantResponseDto;

import java.util.List;

public interface IProductVariantService {

    ProductVariantResponseDto createProductVariant(CreateProductVariantRequestDto createProductVariantRequestDto);

    ProductVariantResponseDto updateProductVariant(UpdateProductVariantRequestDto updateProductVariantRequestDto);

    List<ProductVariantResponseDto> getProductVariants(long productId, int pageNumber, int pageSize);

    ProductVariantResponseDto getProductVariant(long ProductVariantId);

    void deleteProductVariant(long ProductVariantId);
}
