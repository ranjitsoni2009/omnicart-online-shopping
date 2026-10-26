package com.omnicart.product.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Getter
@Setter
public class UpdateProductRequestDto {

    private long id;

    @NotNull(message = "Product Name can't be null")
    private String name;

    @NotNull(message = "Product Description can't be null")
    private String description;

    @NotNull(message = "Brand Id can't be null")
    private Long brandId;

    @NotNull(message = "CategoryId can't be null")
    private Long categoryId;
}
