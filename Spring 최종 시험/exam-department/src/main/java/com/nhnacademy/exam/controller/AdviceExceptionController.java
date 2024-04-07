package com.nhnacademy.exam.controller;

import com.nhnacademy.exam.domain.response.ErrorResponse;
import com.nhnacademy.exam.exception.DepartmentNotFoundException;
import com.nhnacademy.exam.exception.DuplicateDepartmentIdException;
import com.nhnacademy.exam.exception.HeaderException;
import com.nhnacademy.exam.exception.MissingParameterException;
import com.nhnacademy.exam.exception.RequestTypeException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceExceptionController {

    @ExceptionHandler(HeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse headerException(HeaderException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now());
    }

    @ExceptionHandler(DuplicateDepartmentIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse duplicateDepartmentId(DuplicateDepartmentIdException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse departmentNotFound(DepartmentNotFoundException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
    }

    @ExceptionHandler(MissingParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse parameterMiss(MissingParameterException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
    }

    @ExceptionHandler(RequestTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse requestTypeMiss(RequestTypeException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
    }
}
