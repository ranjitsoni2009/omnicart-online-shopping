package com.omnicart.product.service.impl;

import com.omnicart.product.dto.request.CreateProductRequestDto;
import com.omnicart.product.dto.request.UpdateProductRequestDto;
import com.omnicart.product.dto.response.ProductResponseDto;
import com.omnicart.product.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Service
public class ProductServiceServiceImpl implements IProductService {

    @Override
    public ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDto) {
        return null;
    }

    @Override
    public ProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto) {
        return null;
    }

    @Override
    public List<ProductResponseDto> getProducts(int pageNumber, int pageSize) {
        return List.of();
    }

    @Override
    public ProductResponseDto getProduct(long ProductId) {
        return null;
    }

    @Override
    public void deleteProduct(long ProductId) {

    }
}
