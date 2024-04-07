package com.nhnacademy.shoppingmall.product.domain;

import lombok.Getter;

@Getter
public class CategoryProduct {
    private final int categoryId;
    private final int productId;

    public CategoryProduct(int categoryId, int productId) {
        this.categoryId = categoryId;
        this.productId = productId;
    }
}
