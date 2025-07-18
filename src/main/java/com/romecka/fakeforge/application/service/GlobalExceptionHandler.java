package com.romecka.fakeforge.application.service;

import com.romecka.fakeforge.application.api.error.ErrorResponse;
import com.romecka.fakeforge.domain.limit.LimitNotFoundException;
import com.romecka.fakeforge.domain.user.LoginFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ErrorResponse> handleLoginFailure(LoginFailedException ex) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(LimitNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLimitNotFount(LimitNotFoundException ex) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ErrorResponse(ex.getMessage()));
    }

}
