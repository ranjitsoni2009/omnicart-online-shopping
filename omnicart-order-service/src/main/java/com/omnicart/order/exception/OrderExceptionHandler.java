package com.omnicart.order.exception;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(OrderBusinessException.class)
    ResponseEntity<Map<String, String>> business(OrderBusinessException e) {
        HttpStatus s = switch (e.getCode()) {
            case "ORDER_NOT_FOUND" -> HttpStatus.NOT_FOUND;
            case "FORBIDDEN" -> HttpStatus.FORBIDDEN;
            case "INTEGRATION_UNAVAILABLE" -> HttpStatus.SERVICE_UNAVAILABLE;
            default -> HttpStatus.BAD_REQUEST;
        };
        return ResponseEntity.status(s).body(Map.of("code", e.getCode(), "message", e.getMessage()));
    }
}
