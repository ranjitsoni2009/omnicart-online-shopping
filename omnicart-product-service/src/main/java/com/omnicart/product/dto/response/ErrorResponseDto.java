package com.omnicart.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ranjit Soni on 23-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Getter
@Setter
@Builder
public class ErrorResponseDto {

    private String errorMessage;
    private String errorCode;
}
