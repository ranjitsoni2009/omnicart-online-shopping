package com.omnicart.product.service.impl;

import com.omnicart.product.dto.request.CreateProductVariantRequestDto;
import com.omnicart.product.dto.request.UpdateProductVariantRequestDto;
import com.omnicart.product.dto.response.ProductVariantResponseDto;
import com.omnicart.product.entity.Product;
import com.omnicart.product.entity.ProductVariant;
import com.omnicart.product.exception.CustomBusinessException;
import com.omnicart.product.mapper.GenericMapper;
import com.omnicart.product.repo.IProductRepo;
import com.omnicart.product.repo.IProductVariantRepo;
import com.omnicart.product.service.IProductService;
import com.omnicart.product.service.IProductVariantService;
import com.omnicart.product.util.OmniCartProductConstant;
import com.omnicart.product.util.OmniCartProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Service
@RequiredArgsConstructor
public class ProductVariantServiceServiceImpl implements IProductVariantService {

    private final IProductVariantRepo productVariantRepo;
    private final IProductRepo productRepo;
    private final IProductService productService;
    private final GenericMapper genericMapper;

    @Override
    public ProductVariantResponseDto createProductVariant(CreateProductVariantRequestDto variantRequestDto) throws CustomBusinessException {
        Optional<ProductVariant> optionalProductVariant = productVariantRepo.findBySkuId(variantRequestDto.getSkuId());
        if (optionalProductVariant.isPresent()) {
            throw new CustomBusinessException(
                    OmniCartProductConstant.PRODUCT_VARIANT_ALREADY_EXIST_ERROR_MSG,
                    OmniCartProductUtil.getErrorCode(
                            OmniCartProductConstant.BUSINESS_EXCEPTION_TAG, OmniCartProductConstant.PRODUCT_VARIANT_ALREADY_EXIST_ERROR_CODE));
        }

        Product product = productService.getProductById(variantRequestDto.getProductId());
        ProductVariant productVariant = genericMapper.toEntity(variantRequestDto, ProductVariant.class);
        productVariant.setActive(true);
        productVariant.setProduct(product);
        productVariant.setCreatedAt(Instant.now());
        productVariant.setUpdatedAt(Instant.now());

        productVariant = productVariantRepo.save(productVariant);
        return genericMapper.toDto(productVariant, ProductVariantResponseDto.class);
    }

    @Override
    public ProductVariantResponseDto updateProductVariant(UpdateProductVariantRequestDto variantRequestDto) throws CustomBusinessException {
        ProductVariant productVariant = getProductVariantById(variantRequestDto.getProductVariantId());
        productVariant.setPrice(variantRequestDto.getPrice());
        productVariant.setDiscount(variantRequestDto.getDiscount());
        productVariant.setProductAttributes(variantRequestDto.getProductAttributes());
        productVariant.setProductAttributes(variantRequestDto.getProductAttributes());

        productVariant = productVariantRepo.save(productVariant);
        return genericMapper.toDto(productVariant, ProductVariantResponseDto.class);
    }

    @Override
    public List<ProductVariantResponseDto> getProductVariants(long productId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<ProductVariant> productVariantPage = productVariantRepo.findByProductId(productId, pageable);
        List<ProductVariant> productVariants = productVariantPage.getContent();
        List<ProductVariantResponseDto> productVariantResponseDtoList = new ArrayList<>();
        if (!productVariants.isEmpty()) {
            productVariantResponseDtoList = productVariants.stream()
                    .map(productVariant ->
                            genericMapper.toDto(productVariant, ProductVariantResponseDto.class))
                    .toList();
        }
        return productVariantResponseDtoList;
    }

    @Override
    public ProductVariantResponseDto getProductVariant(long productVariantId) throws CustomBusinessException {
        ProductVariant productVariant = getProductVariantById(productVariantId);
        return genericMapper.toDto(productVariant, ProductVariantResponseDto.class);
    }

    @Override
    public void deleteProductVariant(long productVariantId) throws CustomBusinessException {
        ProductVariant productVariant = getProductVariantById(productVariantId);
        productVariant.setActive(false);
        productVariant.setUpdatedAt(Instant.now());
        productVariantRepo.save(productVariant);
    }

    private ProductVariant getProductVariantById(long productVariantId) throws CustomBusinessException {
        return productVariantRepo.findById(productVariantId)
                .orElseThrow(() -> new CustomBusinessException(
                        OmniCartProductConstant.PRODUCT_VARIANT_NOT_FOUND,
                        OmniCartProductUtil.getErrorCode(
                                OmniCartProductConstant.BUSINESS_EXCEPTION_TAG, OmniCartProductConstant.PRODUCT_VARIANT_NOT_FOUND_ERROR_CODE)));
    }
}
