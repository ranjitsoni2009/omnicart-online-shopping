package com.omnicart.order.exception.handler;

import com.omnicart.order.dto.response.ErrorResponseDto;
import com.omnicart.order.exception.CustomBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Ranjit Soni on 15-11-2026.
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
