package com.omnicart.product.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Getter
@Setter
public class UpdateProductVariantRequestDto {

    private long productId;
    private long productVariantId;
    private double price;
    private double discount;
    private String productAttributes;
}
