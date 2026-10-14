package com.omnicart.user.service;

import com.omnicart.user.dto.request.LoginRequestDto;
import com.omnicart.user.dto.response.LoginResponseDto;
import com.omnicart.user.dto.response.TokenResponseDto;

import com.omnicart.user.entity.CustomUserDetails;
import com.omnicart.user.entity.UserSession;
import com.omnicart.user.exception.CustomBusinessException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.time.Instant;
import java.util.Objects;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    private AuthenticationManager authenticationManager;
    private ITokenService tokenService;

    /**
     * @return TokenResponseDto
     */
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws CustomBusinessException {

        Authentication authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        loginRequestDto.getUsername(), loginRequestDto.getPassword()));

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String token = tokenService.generateToken(customUserDetails);

        return LoginResponseDto.builder()
                .accessToken(token)
                .expireIn(600000)
                .tokenType("Bearer")
                .build();
    }


    public String createToken(User user, Instant issuedAt, Instant expireAt) {
        return null;
    }
}
