package com.rectasolutions.moving.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

@RestControllerAdvice
public class BookingExceptionHandler {
    @ExceptionHandler(value = ConnectException.class)
    public ResponseEntity apiConnectionException(){
        return new ResponseEntity("Couldn't connect to remote service",HttpStatus.NOT_ACCEPTABLE);
    }
}
