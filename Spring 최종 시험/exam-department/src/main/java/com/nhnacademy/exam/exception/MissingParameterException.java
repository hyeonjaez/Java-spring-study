package com.nhnacademy.exam.exception;

public class MissingParameterException extends RuntimeException {
    private final String message;

    public MissingParameterException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
