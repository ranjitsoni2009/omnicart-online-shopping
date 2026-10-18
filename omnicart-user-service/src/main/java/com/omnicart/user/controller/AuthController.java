package com.omnicart.user.controller;

import com.omnicart.user.dto.request.LoginRequestDto;
import com.omnicart.user.dto.response.TokenResponseDto;
import com.omnicart.user.exception.CustomBusinessException;
import com.omnicart.user.service.IAuthService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws CustomBusinessException {
        TokenResponseDto loginResponseDto = authService.login(loginRequestDto);
        return ResponseEntity.ok(loginResponseDto);
    }


    @PostMapping("/refresh-access-token")
    public ResponseEntity<TokenResponseDto> refreshAccessToken(@NonNull @RequestHeader("refresh-token") String refreshToken) throws CustomBusinessException {
        TokenResponseDto loginResponseDto = authService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(loginResponseDto);
    }
}
