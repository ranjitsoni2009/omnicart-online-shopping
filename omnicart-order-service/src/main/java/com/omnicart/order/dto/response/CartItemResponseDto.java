package com.omnicart.order.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by Ranjit Soni on 15-11-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Getter
@Builder
public class CartItemResponseDto {

    private long cartItemId;
    private long productId;
    private long productVariantId;
    private long vendorId;
    private String productName;
    private String sku;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal discountPercentage;
    private BigDecimal taxPercentage;
    private Instant createdAt;
}
