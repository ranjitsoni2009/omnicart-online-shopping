package com.omnicart.user.service.impl;

import com.omnicart.user.dto.request.LoginRequestDto;

import com.omnicart.user.dto.response.TokenResponseDto;
import com.omnicart.user.entity.CustomUserDetails;
import com.omnicart.user.exception.CustomBusinessException;
import com.omnicart.user.service.*;
import com.omnicart.user.util.OmniCartUserConstant;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements IAuthService {


    private AuthenticationManager authenticationManager;
    private ITokenService tokenService;
    private IRefreshTokenService refreshTokenService;
    private IUserSessionService userSessionService;
    private ICustomUserDetailsService customUserDetailsService;

    /**
     * @return TokenResponseDto
     */
    @Override
    public TokenResponseDto login(LoginRequestDto loginRequestDto) throws CustomBusinessException {

        Authentication authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        loginRequestDto.getUsername(), loginRequestDto.getPassword()));

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        if (Objects.isNull(customUserDetails)) {
            throw new RuntimeException(("customUserDetails is null"));
        }
        Date currentTime = new Date();
        String token = tokenService.generateToken(customUserDetails, currentTime);
        String refreshToken = refreshTokenService.generateRefreshToken();
        String refreshTokenHash = refreshTokenService.hashRefreshToken(refreshToken);

        Instant expiresAt = Instant.now().plus(Duration.ofDays(7));

        userSessionService.createUserSession(customUserDetails.getUserId(), refreshTokenHash, loginRequestDto.getUsername(), expiresAt, null, null);

        return TokenResponseDto.builder()
                .accessToken(token)
                .expireIn(600000)
                .tokenType(OmniCartUserConstant.BEARER)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public TokenResponseDto refreshAccessToken(String refreshToken) throws CustomBusinessException {

        String refreshTokenHash = refreshTokenService.hashRefreshToken(refreshToken);
        String userName = userSessionService.validateAndReturnUserName(refreshTokenHash);
        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(userName);
        if (!userDetails.isEnabled()
                || !userDetails.isAccountNonLocked()) {
            throw new BadCredentialsException(
                    "User account is unavailable");
        }
        String token = tokenService.generateToken(userDetails, new Date());

        return TokenResponseDto.builder()
                .accessToken(token)
                .expireIn(600000)
                .tokenType(OmniCartUserConstant.BEARER)
                .refreshToken(refreshToken)
                .build();
    }
}
