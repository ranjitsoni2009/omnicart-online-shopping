package com.omnicart.order.exception;

import lombok.Getter;

@Getter
public class OrderBusinessException extends RuntimeException {
    private final String code;

    public OrderBusinessException(String code, String message) {
        super(message);
        this.code = code;
    }
}
