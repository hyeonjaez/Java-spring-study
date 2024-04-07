package com.nhnacademy.shoppingmall.user.domain;

import lombok.Getter;

@Getter
public class UserAddress {
    private final int addressId;
    private final String userId;
    private final String addressName;

    public UserAddress(int addressId, String userId, String addressName) {
        this.addressId = addressId;
        this.userId = userId;
        this.addressName = addressName;
    }
}
