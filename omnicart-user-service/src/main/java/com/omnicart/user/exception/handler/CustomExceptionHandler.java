package com.omnicart.user.exception.handler;

import com.omnicart.user.exception.RequiredFieldNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = RequiredFieldNotFoundException.class)
    public ResponseEntity<String> handleException(RequiredFieldNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
