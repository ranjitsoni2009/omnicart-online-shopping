package com.omnicart.product.service.impl;

import com.omnicart.product.dto.request.CreateProductRequestDto;
import com.omnicart.product.dto.request.UpdateProductRequestDto;
import com.omnicart.product.dto.response.ProductResponseDto;
import com.omnicart.product.entity.Brand;
import com.omnicart.product.entity.Category;
import com.omnicart.product.entity.Product;
import com.omnicart.product.exception.CustomBusinessException;
import com.omnicart.product.mapper.GenericMapper;
import com.omnicart.product.repo.IProductRepo;
import com.omnicart.product.service.IBrandService;
import com.omnicart.product.service.ICategoryService;
import com.omnicart.product.service.IProductService;
import com.omnicart.product.util.OmniCartProductConstant;
import com.omnicart.product.util.OmniCartProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Service
@RequiredArgsConstructor
public class ProductServiceServiceImpl implements IProductService {

    private final IProductRepo productRepo;
    private final ICategoryService categoryService;
    private final IBrandService brandService;
    private final GenericMapper genericMapper;

    @Override
    public ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDto) throws CustomBusinessException {
        throwExceptionIfProductExist(createProductRequestDto.getName(), createProductRequestDto.getCategoryId(), createProductRequestDto.getBrandId());

        Product product = getProduct(createProductRequestDto.getName(), createProductRequestDto.getCategoryId(), createProductRequestDto.getBrandId());
        product.setCreatedAt(Instant.now());
        product = productRepo.save(product);
        return genericMapper.toDto(product, ProductResponseDto.class);
    }

    private void throwExceptionIfProductExist(String productName, long categoryId, long brandId) throws CustomBusinessException {
        Optional<Product> optionalProduct = productRepo.findByNameAndCategoryIdAndBrandId(
                productName, categoryId, brandId);
        if (optionalProduct.isPresent()) {
            throw new CustomBusinessException(
                    OmniCartProductConstant.PRODUCT_ALREADY_EXIST_ERROR_MSG,
                    OmniCartProductUtil.getErrorCode(
                            OmniCartProductConstant.BUSINESS_EXCEPTION_TAG, OmniCartProductConstant.PRODUCT_ALREADY_EXIST_ERROR_CODE));
        }
    }

    private Product getProduct(String productName, long categoryId, long brandId) throws CustomBusinessException {

        Category category = categoryService.getCategoryById(categoryId);
        Brand brand = brandService.getBrandById(brandId);

        return Product.builder()
                .name(productName)
                .isActive(true)
                .category(category)
                .brand(brand)
                .updatedAt(Instant.now())
                .build();
    }

    @Override
    public ProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto) throws CustomBusinessException {
        throwExceptionIfProductExist(updateProductRequestDto.getName(), updateProductRequestDto.getCategoryId(), updateProductRequestDto.getBrandId());

        Product product = getProductById(updateProductRequestDto.getId());
        if (product.getCategory().getId() != updateProductRequestDto.getCategoryId()) {
            Category category = categoryService.getCategoryById(updateProductRequestDto.getCategoryId());
            product.setCategory(category);
        }

        if (product.getBrand().getId() != updateProductRequestDto.getBrandId()) {
            Brand brand = brandService.getBrandById(updateProductRequestDto.getBrandId());
            product.setBrand(brand);
        }

        product.setName(updateProductRequestDto.getName());
        product.setDescription(updateProductRequestDto.getDescription());
        product.setUpdatedAt(Instant.now());
        product = productRepo.save(product);
        return genericMapper.toDto(product, ProductResponseDto.class);
    }

    @Override
    public List<ProductResponseDto> getProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = productRepo.findAll(pageable);
        List<Product> products = productPage.getContent();
        return products.stream()
                .map(product ->
                        genericMapper.toDto(product, ProductResponseDto.class))
                .toList();
    }

    @Override
    public ProductResponseDto getProduct(long productId) throws CustomBusinessException {
        Product product = getProductById(productId);
        return genericMapper.toDto(product, ProductResponseDto.class);
    }

    @Override
    public void deleteProduct(long productId) throws CustomBusinessException {
        Product product = getProductById(productId);
        product.setActive(false);
        product.setUpdatedAt(Instant.now());
        productRepo.save(product);
    }

    @Override
    public Product getProductById(long productId) throws CustomBusinessException {
        return productRepo.findById(productId).orElseThrow(() ->
                new CustomBusinessException(
                        OmniCartProductConstant.PRODUCT_NOT_FOUND,
                        OmniCartProductUtil.getErrorCode(
                                OmniCartProductConstant.BUSINESS_EXCEPTION_TAG, OmniCartProductConstant.PRODUCT_NOT_FOUND_ERROR_CODE)));
    }
}
