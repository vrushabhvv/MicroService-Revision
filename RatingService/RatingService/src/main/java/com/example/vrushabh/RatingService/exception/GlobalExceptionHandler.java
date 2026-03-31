package com.example.vrushabh.RatingService.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    String handlerMethod(){
        return "RunTimeException Occurred";
    }
}
