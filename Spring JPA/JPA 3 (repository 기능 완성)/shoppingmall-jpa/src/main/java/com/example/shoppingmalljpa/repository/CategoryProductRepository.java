package com.example.shoppingmalljpa.repository;

import com.example.shoppingmalljpa.entity.ProductCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryProductRepository extends JpaRepository<ProductCategory, Long> {
    List<ProductCategory> findProductCategoryByCategory_CategoryId(Long categoryId);
    List<ProductCategory> findProductCategoryByProduct_ProductId(Long productId);
}