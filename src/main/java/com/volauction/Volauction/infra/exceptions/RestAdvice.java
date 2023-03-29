package com.volauction.Volauction.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFoundEntity(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
}
