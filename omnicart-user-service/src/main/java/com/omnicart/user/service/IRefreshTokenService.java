package com.omnicart.user.service;

public interface IRefreshTokenService {

    String generateRefreshToken();
    String hashRefreshToken(String refreshToken);
}
