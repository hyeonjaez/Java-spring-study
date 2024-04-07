package com.nhnacademy.shoppingmall.shoppingcart.domain;

import lombok.Getter;

@Getter
public class ShoppingCart {
    private final String productId;
    private final String userId;
    private final int amount;

    public ShoppingCart(String productId, String userId, int amount) {
        this.productId = productId;
        this.userId = userId;
        this.amount = amount;
    }
}
