package br.com.totvs.hotel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ApplicationController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> campoInvalidoHandler(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        FieldError error = result.getFieldError();
        String errorMessage = error.getDefaultMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> responseStatusHandler(ResponseStatusException exception) {
        return new ResponseEntity<String>(exception.getReason(), exception.getStatusCode());
    }

}
