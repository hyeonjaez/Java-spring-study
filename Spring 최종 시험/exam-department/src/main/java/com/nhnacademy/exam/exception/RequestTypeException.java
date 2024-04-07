package com.nhnacademy.exam.exception;

public class RequestTypeException extends RuntimeException {
    private final String message;

    public RequestTypeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
