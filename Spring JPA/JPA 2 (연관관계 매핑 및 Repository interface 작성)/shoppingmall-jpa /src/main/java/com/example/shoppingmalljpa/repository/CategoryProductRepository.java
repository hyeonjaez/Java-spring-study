package com.example.shoppingmalljpa.repository;

import com.example.shoppingmalljpa.domain.ProductCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryProductRepository extends JpaRepository<ProductCategory.Pk, Long> {

    List<Long> findCategoryId(Long productId);

    List<Long> findProductId(Long categoryId);

    int addCategoryProduct(Long categoryId, Long productId);

    int deleteCategoryProduct(Long productId);


}