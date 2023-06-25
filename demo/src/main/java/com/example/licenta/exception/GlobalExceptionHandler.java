package com.example.licenta.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException apiException) {
        ApiError apiError = new ApiError(
                apiException.getMessage(),
                apiException.getErrorKeys().name()
        );
        return new ResponseEntity<>(apiError, apiException.getHttpStatus());
    }
}
