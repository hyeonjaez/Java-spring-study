package com.nhnacademy.shoppingmall.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId) {
        super(String.format("user not found: %s", userId));
    }
}
