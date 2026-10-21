package com.omnicart.product.dto.request;

import jakarta.validation.constraints.NotNull;

/**
 * Created by Ranjit Soni on 19-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
public class CreateCategoryRequestDto {

    @NotNull(message = "Category Name can't be null")
    private String name;
}
