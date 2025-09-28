package com.romecka.fakeforge.application.service;

import com.romecka.fakeforge.application.api.generic.ErrorResponse;
import com.romecka.fakeforge.domain.limit.LimitExceededException;
import com.romecka.fakeforge.domain.limit.LimitNotFoundException;
import com.romecka.fakeforge.domain.user.LimitForUserNotFoundException;
import com.romecka.fakeforge.domain.user.LoginFailedException;
import com.romecka.fakeforge.domain.user.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ErrorResponse> handleLoginFailure(LoginFailedException ex) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(LimitNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLimitNotFound(LimitNotFoundException ex) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(LimitExceededException.class)
    public ResponseEntity<ErrorResponse> handleLimitExceeded(LimitExceededException ex) {
        return ResponseEntity.status(TOO_MANY_REQUESTS).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(LimitForUserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLimitNotFound(LimitForUserNotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND).body(new ErrorResponse(ex.getMessage()));
    }

}
