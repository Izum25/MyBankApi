package com.example.mybankapplication.controller;

import com.example.mybankapplication.exception.NotFoundException;
import com.example.mybankapplication.model.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends RuntimeException{
    @ExceptionHandler(NotFoundException.class)
    public ExceptionDto handler(NotFoundException exception){
        log.error("Action.Log.Error not found: {}", exception.getMessage());
        return new ExceptionDto(exception.getMessage());
    }

}
