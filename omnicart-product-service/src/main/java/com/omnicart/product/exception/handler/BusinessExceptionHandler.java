package com.omnicart.product.exception.handler;

import com.omnicart.product.dto.response.ErrorResponseDto;
import com.omnicart.product.exception.CustomBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@RestControllerAdvice
public class BusinessExceptionHandler{

    @ExceptionHandler(CustomBusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleBusinessException(CustomBusinessException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorResponseDto.builder().errorMessage(ex.getMessage()).errorCode(ex.getErrorCode()).build());
    }
}
