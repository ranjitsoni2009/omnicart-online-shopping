package com.omnicart.user.service;

import com.omnicart.user.dto.request.LoginRequestDto;
import com.omnicart.user.dto.response.LoginResponseDto;
import com.omnicart.user.dto.response.TokenResponseDto;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.time.Instant;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    private AuthenticationManager authenticationManager;

    /**
     * @return TokenResponseDto
     */
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        loginRequestDto.getUsername(), loginRequestDto.getPassword()));

        org.springframework.security.core.userdetails.User user = (User) authentication.getPrincipal();

        return new LoginResponseDto("Sample Token");
    }


    public String createToken(UserPrincipal userPrincipal, Instant issuedAt, Instant expireAt) {
        return null;
    }
}
