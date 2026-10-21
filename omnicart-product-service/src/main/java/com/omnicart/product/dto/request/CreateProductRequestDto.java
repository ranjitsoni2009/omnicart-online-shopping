package com.omnicart.product.dto.request;

import jakarta.validation.constraints.NotNull;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
public class CreateProductRequestDto {

    @NotNull(message = "Product Name can't be null")
    private String name;

    @NotNull(message = "Product Description can't be null")
    private String description;

    @NotNull(message = "Product Name can't be null")
    private Long categoryId;
}
