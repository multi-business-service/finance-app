package com.ak.finance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ResponseEntityExceptionController extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> generalException(Exception exception, WebRequest  request){
        return handleExceptionInternal(exception, exception.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
