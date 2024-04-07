package com.nhnacademy.shoppingmall.product.exception;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(int userId) {
        super(String.format("product already exist: %s", userId));
    }
}
