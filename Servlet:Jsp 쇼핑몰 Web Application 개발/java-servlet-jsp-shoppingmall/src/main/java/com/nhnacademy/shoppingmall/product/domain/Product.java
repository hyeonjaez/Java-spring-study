package com.nhnacademy.shoppingmall.product.domain;

import lombok.Getter;

@Getter
public class Product {
    private final int productId;
    private final String modelNumber;
    private final String modelName;
    private final String productImage;
    private final long unitCost;
    private final String description;


    public Product(int productId, String modelNumber, String modelName, String productImage,
                   long unitCost, String description) {
        this.productId = productId;
        this.modelNumber = modelNumber;
        this.modelName = modelName;
        this.productImage = productImage;
        this.unitCost = unitCost;
        this.description = description;
    }

}
