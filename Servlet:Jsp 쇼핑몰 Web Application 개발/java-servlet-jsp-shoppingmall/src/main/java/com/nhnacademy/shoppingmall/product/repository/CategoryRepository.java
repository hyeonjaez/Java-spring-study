package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.product.domain.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> categoryList();

    Optional<Category> findCategoryById(int categoryId);
}
