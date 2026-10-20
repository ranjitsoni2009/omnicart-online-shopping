package com.omnicart.product.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Ranjit Soni on 19-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = exception.getBindingResult()
                .getFieldErrors() // Gets List<FieldError>
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,                              // Key: field name (e.g., "email")
                        fieldError -> fieldError.getDefaultMessage() != null
                                ? fieldError.getDefaultMessage()
                                : "Invalid value",                             // Value: error message
                        (existingMessage, newMessage) -> existingMessage + "; " + newMessage // Merge duplicate field errors
                ));

        return ResponseEntity.badRequest().body(errorMap);
    }
}
