package com.omnicart.product.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Getter
@Setter
@AllArgsConstructor
public class UpdateBrandRequestDto {

    private long id;

    @NotNull(message = "Brand Name can't be null")
    private String name;

}
