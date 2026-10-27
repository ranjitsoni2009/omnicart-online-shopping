package com.omnicart.product.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVariantResponseDto {

    private long id;
    private long productId;
    private long productVariantId;
    private String skuId;
    private double price;
    private double discount;
    private boolean isActive;
    private String productAttributes;
    private String createdAt;
    private String updateAt;
}
