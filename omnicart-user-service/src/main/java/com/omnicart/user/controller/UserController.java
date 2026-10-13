package com.omnicart.user.controller;

import com.omnicart.user.dto.request.UserRegisterRequestDto;
import com.omnicart.user.dto.response.UserRegisterResponseDto;
import com.omnicart.user.exception.CustomBusinessException;
import com.omnicart.user.exception.RequiredFieldNotFoundException;
import com.omnicart.user.service.IUserService;
import com.omnicart.user.validator.UserRegisterRequestValidator;
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
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) throws Exception {
        UserRegisterRequestValidator.validateRequiredField(userRegisterRequestDto);
        UserRegisterResponseDto userRegisterResponseDto = userService.register(userRegisterRequestDto);
        return ResponseEntity.ok(userRegisterResponseDto);
    }


}
