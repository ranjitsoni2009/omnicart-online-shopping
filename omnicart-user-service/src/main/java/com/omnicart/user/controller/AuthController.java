package com.omnicart.user.controller;

import com.omnicart.user.dto.request.LoginRequestDto;
import com.omnicart.user.dto.response.LoginResponseDto;
import com.omnicart.user.service.AuthService;
import com.omnicart.user.service.IAuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {

        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);

        return ResponseEntity.ok(loginResponseDto);
    }

/*
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDto> logout() {
        LoginResponseDto loginResponseDto = new LoginResponseDto(null);
        return ResponseEntity.ok(loginResponseDto);
    }*/
}
