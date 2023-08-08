package com.example.reservas.controller.exceptions;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.reservas.service.exceptions.InvalidStatusException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerHandlerException {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        StandartError erro = new StandartError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<StandartError> invalidStatus(InvalidStatusException ex) {
        StandartError error = new StandartError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
