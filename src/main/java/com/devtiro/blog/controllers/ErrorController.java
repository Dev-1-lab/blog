package com.devtiro.blog.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Slf4j
public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        ApiErrorResponse apiErrorResponse = ApiErrorResponse
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error(ex.getMessage(), ex);
        ApiErrorResponse errorResponse= ApiErrorResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }




    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalStateException(IllegalStateException ex) {
        log.error(ex.getMessage(), ex);
        ApiErrorResponse errorResponse= ApiErrorResponse
                .builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

}
