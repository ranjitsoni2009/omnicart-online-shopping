package com.omnicart.user.controller;

import com.omnicart.user.dto.request.UserRegisterRequestDto;
import com.omnicart.user.dto.response.UserRegisterResponseDto;
import com.omnicart.user.exception.RequiredFieldNotFoundException;
import com.omnicart.user.validator.UserRegisterRequestValidator;
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
@RequestMapping(value = "/api/users")
public class UserController {


    @PostMapping(value = "/register")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) throws RequiredFieldNotFoundException {

        UserRegisterRequestValidator.validateRequiredField(userRegisterRequestDto);

        UserRegisterResponseDto userRegisterResponseDto = UserRegisterResponseDto.builder()
                .firstName(userRegisterRequestDto.getFirstName())
                .lastName(userRegisterRequestDto.getLastName())
                .email(userRegisterRequestDto.getEmail())
                .mobile(userRegisterRequestDto.getMobile())
                .build();
        return ResponseEntity.ok(userRegisterResponseDto);
    }


}
