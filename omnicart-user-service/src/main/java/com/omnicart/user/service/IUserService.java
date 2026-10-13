package com.omnicart.user.service;

import com.omnicart.user.dto.request.UserRegisterRequestDto;
import com.omnicart.user.dto.response.UserRegisterResponseDto;

public interface IUserService {

    UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto);
}