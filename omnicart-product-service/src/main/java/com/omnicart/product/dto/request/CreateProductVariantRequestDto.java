package com.omnicart.product.dto.request;

import lombok.*;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Getter
@Setter
public class CreateProductVariantRequestDto {

    private long productId;
    private String skuId;
    private double price;
    private double discount;
    private String productAttributes;
}
