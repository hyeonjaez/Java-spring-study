package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.product.service.CategoryService;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> categoryList() {
        return categoryRepository.categoryList();
    }

    @Override
    public Category findCategoryById(int categoryId) {
        return categoryRepository.findCategoryById(categoryId).orElse(null);
    }
}
