package com.example.licenta.exception;

public class ApiError {

    private final String message;

    public ApiError(String message, String errorKeys) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
