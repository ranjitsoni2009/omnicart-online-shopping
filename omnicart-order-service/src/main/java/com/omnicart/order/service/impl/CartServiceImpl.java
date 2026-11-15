package com.omnicart.order.service.impl;

import com.omnicart.order.dto.request.CreateCartItemRequestDto;
import com.omnicart.order.dto.response.CartItemResponseDto;
import com.omnicart.order.entity.CartItem;
import com.omnicart.order.exception.CustomBusinessException;
import com.omnicart.order.exception.OrderBusinessException;
import com.omnicart.order.port.CartPort;
import com.omnicart.order.repo.ICartItemRepo;
import com.omnicart.order.service.ICartService;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.omnicart.order.util.OmniCartOrderConstant;
import com.omnicart.order.util.OmniCartOrderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {

    private final ICartItemRepo cartItemRepo;

    @Override
    public CartItemResponseDto addItemToCart(CreateCartItemRequestDto requestDto) throws CustomBusinessException {
        Optional<CartItem> optionalCartItem = cartItemRepo.findByProductVariantIdAndVendorId(requestDto.getProductVariantId(), requestDto.getVendorId());
        if (optionalCartItem.isPresent()) {
            throw OmniCartOrderUtil.businessException(OmniCartOrderConstant.ITEM_IN_CART_ALREADY_EXIST_ERROR_MSG, OmniCartOrderConstant.ITEM_IN_CART_ALREADY_EXIST_ERROR_CODE);
        }

        // Add item to cart logic here
        CartItem cartItem = getCartItem(requestDto);
        cartItem = cartItemRepo.save(cartItem);
        return toResponse(cartItem);
    }

    @Override
    public List<CartItemResponseDto> getCurrentCart() {
        List<CartItem> cartItems = cartItemRepo.findByCustomerId(getCustomerId());
        return cartItems.stream()
                .map(CartServiceImpl::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void clearCurrentCart() {
        cartItemRepo.deleteAllByCustomerId(getCustomerId());
    }

    private static CartItemResponseDto toResponse(CartItem cartItem) {
        return CartItemResponseDto.builder()
                .cartItemId(cartItem.getId())
                .productId(cartItem.getProductId())
                .productVariantId(cartItem.getProductVariantId())
                .vendorId(cartItem.getVendorId())
                .productName(cartItem.getProductName())
                .sku(cartItem.getSku())
                .quantity(cartItem.getQuantity())
                .unitPrice(cartItem.getUnitPrice())
                .taxPercentage(cartItem.getTaxPercentage())
                .discountPercentage(cartItem.getDiscountPercentage())
                .createdAt(cartItem.getCreatedAt())
                .build();
    }

    private CartItem getCartItem(CreateCartItemRequestDto requestDto) {
        Instant now = Instant.now();
        return CartItem.builder()
                .productId(requestDto.getProductId())
                .productVariantId(requestDto.getProductVariantId())
                .vendorId(requestDto.getVendorId())
                .customerId(getCustomerId())
                .productName(requestDto.getProductName())
                .sku(requestDto.getSku())
                .quantity(requestDto.getQuantity())
                .unitPrice(requestDto.getUnitPrice())
                .discountPercentage(requestDto.getDiscountPercentage())
                .taxPercentage(requestDto.getTaxPercentage())
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    private long getCustomerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new OrderBusinessException("FORBIDDEN", "Authenticated user required");
        }

        try {
            return Long.parseLong(authentication.getName());
        } catch (NumberFormatException e) {
            throw new OrderBusinessException("FORBIDDEN", "Authenticated user required");
        }
    }
}
