package com.omnicart.product.service;

import com.omnicart.product.dto.request.CreateProductRequestDto;
import com.omnicart.product.dto.request.UpdateProductRequestDto;
import com.omnicart.product.dto.response.ProductResponseDto;
import com.omnicart.product.entity.Product;
import com.omnicart.product.exception.CustomBusinessException;

import java.util.List;

public interface IProductService {

    ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDto) throws CustomBusinessException;

    ProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto) throws CustomBusinessException;

    List<ProductResponseDto> getProducts(int pageNumber, int pageSize);

    ProductResponseDto getProduct(long ProductId) throws CustomBusinessException;

    void deleteProduct(long ProductId) throws CustomBusinessException;

    Product getProductById(long productId) throws CustomBusinessException;
}
