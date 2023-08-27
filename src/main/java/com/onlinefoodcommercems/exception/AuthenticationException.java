package com.onlinefoodcommercems.exception;

import lombok.Getter;


@Getter
public class AuthenticationException extends RuntimeException {
    private final String code;
    private final String message;
    private final Integer status;

    public AuthenticationException(String message, String code, Integer status) {
        super(message);
        this.message = message;
        this.code = code;
        this.status = status;
    }
}
