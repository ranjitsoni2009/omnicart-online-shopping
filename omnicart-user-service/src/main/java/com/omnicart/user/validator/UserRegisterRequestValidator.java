package com.omnicart.user.validator;

import com.omnicart.user.dto.request.UserRegisterRequestDto;
import com.omnicart.user.exception.RequiredFieldNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
public class UserRegisterRequestValidator {

    public void validateRequiredField(UserRegisterRequestDto userRegisterRequestDto) throws RequiredFieldNotFoundException {
        if (Objects.isNull(userRegisterRequestDto)) {
            throw new RequiredFieldNotFoundException("userRegisterRequestDto is null");
        }

        if (!StringUtils.hasText(userRegisterRequestDto.getFirstName())) {
            throw new RequiredFieldNotFoundException("FirstName not found");
        }

        if (!StringUtils.hasText(userRegisterRequestDto.getLastName())) {
            throw new RequiredFieldNotFoundException("LastName not found");
        }

        if (!StringUtils.hasText(userRegisterRequestDto.getEmail())) {
            throw new RequiredFieldNotFoundException("Email not found");
        }

        if (!StringUtils.hasText(userRegisterRequestDto.getMobile())) {
            throw new RequiredFieldNotFoundException("Mobile number not found");
        }
    }
}
