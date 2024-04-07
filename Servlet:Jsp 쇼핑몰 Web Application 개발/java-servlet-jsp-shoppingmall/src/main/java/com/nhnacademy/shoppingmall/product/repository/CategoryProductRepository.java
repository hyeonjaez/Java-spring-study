package com.nhnacademy.shoppingmall.product.repository;

import java.util.List;

public interface CategoryProductRepository {
    List<Integer> findCategoryId(int productId);

    List<Integer> findProductId(int categoryId);

    int addCategoryProduct(int categoryId, int productId);

    int deleteCategoryProduct(int productId);
}