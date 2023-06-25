package com.example.licenta.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private final String message;
    private final ErrorKeys errorKeys;
    private final HttpStatus httpStatus;

    public ApiException(String message, ErrorKeys errorKeys, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.errorKeys = errorKeys;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ErrorKeys getErrorKeys() {
        return errorKeys;
    }
}

