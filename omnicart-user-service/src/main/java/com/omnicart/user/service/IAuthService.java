package com.omnicart.user.service;

import com.omnicart.user.dto.request.LoginRequestDto;
import com.omnicart.user.dto.response.TokenResponseDto;
import com.omnicart.user.exception.CustomBusinessException;

public interface IAuthService {

    public TokenResponseDto login(LoginRequestDto request) throws CustomBusinessException;

    TokenResponseDto refreshAccessToken(String refreshToken) throws CustomBusinessException;
}
