package com.omnicart.user.validator;

import com.omnicart.user.dto.request.UserRegisterRequestDto;
import com.omnicart.user.exception.RequiredFieldNotFoundException;
import com.omnicart.user.util.OmniCartUserConstant;
import com.omnicart.user.util.OmniCartUtil;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
public class UserRegisterRequestValidator {

    public static void validateRequiredField(UserRegisterRequestDto userRegisterRequestDto) throws RequiredFieldNotFoundException {
        if (Objects.isNull(userRegisterRequestDto)) {
            throw new RequiredFieldNotFoundException(
                    "userRegisterRequestDto is null",
                    OmniCartUtil.getErrorCode(OmniCartUserConstant.REQUEST_EXCEPTION_TAG, OmniCartUserConstant.REQUIRED_FIELD_ERROR_CODE));
        }

        if (!StringUtils.hasText(userRegisterRequestDto.getFirstName())) {
            throw new RequiredFieldNotFoundException(
                    "FirstName not found",
                    OmniCartUtil.getErrorCode(OmniCartUserConstant.REQUEST_EXCEPTION_TAG, OmniCartUserConstant.REQUIRED_FIELD_ERROR_CODE));
        }

        if (!StringUtils.hasText(userRegisterRequestDto.getLastName())) {
            throw new RequiredFieldNotFoundException(
                    "LastName not found",
                    OmniCartUtil.getErrorCode(OmniCartUserConstant.REQUEST_EXCEPTION_TAG, OmniCartUserConstant.REQUIRED_FIELD_ERROR_CODE));
        }

        if (!StringUtils.hasText(userRegisterRequestDto.getEmailId())) {
            throw new RequiredFieldNotFoundException(
                    "Email-Id not found",
                    OmniCartUtil.getErrorCode(OmniCartUserConstant.REQUEST_EXCEPTION_TAG, OmniCartUserConstant.REQUIRED_FIELD_ERROR_CODE));
        }

        if (!StringUtils.hasText(userRegisterRequestDto.getPhoneNumber())) {
            throw new RequiredFieldNotFoundException(
                    "Phone number not found",
                    OmniCartUtil.getErrorCode(OmniCartUserConstant.REQUEST_EXCEPTION_TAG, OmniCartUserConstant.REQUIRED_FIELD_ERROR_CODE));
        }
    }
}
