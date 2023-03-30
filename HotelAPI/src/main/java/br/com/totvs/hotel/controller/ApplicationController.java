package br.com.totvs.hotel.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> campoInvalidoHandler(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        return new ResponseEntity<List<String>>(result.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> responseStatusHandler(ResponseStatusException exception) {
        return new ResponseEntity<String>(exception.getReason(), exception.getStatusCode());
    }

}
