package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findProductById(int productId);

    int save(Product product);

    int deleteByProductId(int productId);

    int update(Product product);

    int productTotalCount();

    List<Product> findAllProduct();

    int countByProductId(int productId);

    Page<Product> findAll(int page, int pageSize);

    int getLastIdNumber();

    Page<Product> findProductByCategory(int categoryId, int page, int pageSize);

    int countProductByCategoryId(int categoryId);
}