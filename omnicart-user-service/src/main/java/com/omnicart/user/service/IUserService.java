package com.omnicart.user.service;

import com.omnicart.user.dto.request.UserRegisterRequestDto;
import com.omnicart.user.dto.response.UserRegisterResponseDto;
import com.omnicart.user.exception.CustomBusinessException;

public interface IUserService {

    UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto) throws CustomBusinessException;
}