package com.nhnacademy.shoppingmall.product.service;

import java.util.List;

public interface CategoryProductService {

    List<Integer> findCategoryIdByProductId(int productId);

    List<Integer> findProductIdByCategoryId(int categoryId);

    void addCategoryProduct(List<Integer> categoryId, int productId);

    void deleteCategoryProduct(int productId);

    void updateCategoryProduct(List<Integer> categoryId, int productId);
}
