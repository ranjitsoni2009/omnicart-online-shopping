package com.omnicart.user.controller;

import com.omnicart.user.dto.request.LoginRequestDto;
import com.omnicart.user.dto.response.LoginResponseDto;
import com.omnicart.user.dto.response.UserRegisterResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
public class AuthController {


    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = new LoginResponseDto(null);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDto> logout() {
        LoginResponseDto loginResponseDto = new LoginResponseDto(null);
        return ResponseEntity.ok(loginResponseDto);
    }
}
