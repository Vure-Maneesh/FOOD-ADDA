package com.foodAdda.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.foodAdda.utility.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> userHandlerException(UserAlreadyExistException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setError("user ALready Exist!");
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(DuplicateRestaurant.class)
    public ResponseEntity<?> handleDuplicateRestuarant(DuplicateRestaurant ex) {

        ErrorResponse err = new ErrorResponse();
        err.setError("Restaurant already exists");
        err.setStatus(HttpStatus.CONFLICT.value());
        err.setMessage(ex.getMessage());
        err.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleUserAlreadyExistsExceptions(RuntimeException ex) {
        System.out.println("Printing Exception message");
        return new ResponseEntity<String>("Printing SOUT for Exception", HttpStatus.CONFLICT);
    }
}
