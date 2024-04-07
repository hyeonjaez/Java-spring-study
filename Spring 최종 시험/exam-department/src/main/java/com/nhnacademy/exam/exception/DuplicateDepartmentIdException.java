package com.nhnacademy.exam.exception;

public class DuplicateDepartmentIdException extends RuntimeException {
    private final String message;

    public DuplicateDepartmentIdException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
