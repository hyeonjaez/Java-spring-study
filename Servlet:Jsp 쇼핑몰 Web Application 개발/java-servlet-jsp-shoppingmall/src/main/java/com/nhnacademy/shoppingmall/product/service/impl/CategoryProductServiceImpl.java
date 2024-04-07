package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.product.repository.CategoryProductRepository;
import com.nhnacademy.shoppingmall.product.service.CategoryProductService;
import java.util.List;

public class CategoryProductServiceImpl implements CategoryProductService {
    private final CategoryProductRepository categoryProductRepository;

    public CategoryProductServiceImpl(CategoryProductRepository categoryProductRepository) {
        this.categoryProductRepository = categoryProductRepository;
    }

    @Override
    public List<Integer> findCategoryIdByProductId(int productId) {
        return categoryProductRepository.findCategoryId(productId);
    }

    @Override
    public List<Integer> findProductIdByCategoryId(int categoryId) {
        return categoryProductRepository.findProductId(categoryId);
    }

    @Override
    public void addCategoryProduct(List<Integer> categoryId, int productId) {
        categoryId.stream()
                .forEach(id -> this.categoryProductRepository.addCategoryProduct(id, productId));
    }

    @Override
    public void deleteCategoryProduct(int productId) {

        categoryProductRepository.deleteCategoryProduct(productId);
    }

    @Override
    public void updateCategoryProduct(List<Integer> categoryId, int productId) {
        deleteCategoryProduct(productId);
        addCategoryProduct(categoryId, productId);
    }
}