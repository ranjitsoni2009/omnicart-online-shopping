package com.omnicart.order.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Ranjit Soni on 15-11-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Getter
@Setter
public class CreateCartItemRequestDto {

    private long productId;
    private long productVariantId;
    private long vendorId;
    private String productName;
    private String sku;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal discountPercentage;
    private BigDecimal taxPercentage;
}
