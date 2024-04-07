package com.example.shoppingmalljpa.domain;

import java.math.BigDecimal;

public interface ProductDto {
    Long getProductId();
    String getModelName();

    String getProductImage();

    BigDecimal getUnitCost();

    interface ProductDetailDto {
        String getModelNumber();

        String getDescription();

    }
}