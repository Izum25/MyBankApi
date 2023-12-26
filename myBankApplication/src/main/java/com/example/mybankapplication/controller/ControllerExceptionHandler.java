package com.example.mybankapplication.controller;

import com.example.mybankapplication.exception.CustomerNotFoundException;
import com.example.mybankapplication.model.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends RuntimeException{
    @ExceptionHandler(CustomerNotFoundException.class)
    public ExceptionDto handler(CustomerNotFoundException exception){
        log.error("Action.Log.Error not found: {}", exception.getMessage());
        return new ExceptionDto(exception.getMessage());
    }
}
