package com.omnicart.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Data
@Builder
@AllArgsConstructor
public class TokenResponseDto {

    private String accessToken;
    private String tokenType;
    private int expireIn;
    private String refreshToken;

}
