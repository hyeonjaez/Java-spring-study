package com.nhnacademy.shoppingmall.product.domain;

import lombok.Getter;

@Getter
public class Category {
    private final int categoryId;
    private final String categoryName;

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}