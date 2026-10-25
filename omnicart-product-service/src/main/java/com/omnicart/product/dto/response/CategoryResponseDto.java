package com.omnicart.product.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by Ranjit Soni on 19-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {

    private long id;
    private String name;
    private boolean isActive;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BrandResponseDto> brandList;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ProductResponseDto> productList;
}
