package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.product.domain.Category;
import java.util.List;

public interface CategoryService {

    List<Category> categoryList();

    Category findCategoryById(int categoryId);

}
