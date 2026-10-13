package com.omnicart.user.exception;

import lombok.RequiredArgsConstructor;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

public class RequiredFieldNotFoundException extends Exception {

    public RequiredFieldNotFoundException(String message) {
        super(message);
    }
}
