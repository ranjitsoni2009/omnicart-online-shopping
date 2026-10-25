package com.omnicart.product.dto.request;

import com.omnicart.product.dto.response.BrandResponseDto;
import com.omnicart.product.entity.Brand;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by Ranjit Soni on 19-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Getter
@Setter
public class UpdateCategoryRequestDto {

    private long id;
    private String name;
    private Map<Long, Boolean> brandMap;
    private Map<Long, Boolean> productMap;
}
