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
public class CreateBrandRequestDto {

    @NotNull(message = "BrandName is not available")
    private String name;
}
