package com.example.licenta.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException apiException) {
        // 1. Create payload containing exception details
        ApiError apiError = new ApiError(
                apiException.getMessage(),
                apiException.getErrorKeys().name()
        );
        // 2. Return response entity
        return new ResponseEntity<>(apiError, apiException.getHttpStatus());
    }
}
