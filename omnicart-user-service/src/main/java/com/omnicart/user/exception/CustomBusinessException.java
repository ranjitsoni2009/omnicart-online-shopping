package com.omnicart.user.exception;

import lombok.Getter;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Getter
public class CustomBusinessException extends Exception {

    private final String errorCode;

    public CustomBusinessException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
