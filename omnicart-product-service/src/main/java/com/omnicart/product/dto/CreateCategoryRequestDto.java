package com.omnicart.product.dto;

import com.omnicart.product.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Created by Ranjit Soni on 19-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
public class CreateCategoryRequestDto {

    @NotNull(message = "Category Name can't be null")
    private String name;
}
