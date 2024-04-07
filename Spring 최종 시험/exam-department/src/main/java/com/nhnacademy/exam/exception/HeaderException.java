package com.nhnacademy.exam.exception;

public class HeaderException extends RuntimeException {
    private String message;

    public HeaderException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
