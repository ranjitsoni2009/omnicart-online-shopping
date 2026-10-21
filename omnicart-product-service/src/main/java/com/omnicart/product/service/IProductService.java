package com.omnicart.product.service;

import com.omnicart.product.dto.request.CreateProductRequestDto;
import com.omnicart.product.dto.request.UpdateProductRequestDto;
import com.omnicart.product.dto.response.ProductResponseDto;

import java.util.List;

public interface IProductService {

    ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDto);

    ProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto);

    List<ProductResponseDto> getProducts(int pageNumber, int pageSize);

    ProductResponseDto getProduct(long ProductId);

    void deleteProduct(long ProductId);
}
