package com.example.shoppingmalljpa.repository;

import com.example.shoppingmalljpa.domain.ProductDto;
import com.example.shoppingmalljpa.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    ProductDto findByProductId(Long productId);
    ProductDto.ProductDetailDto findDetailByProductId(Long productId);
    void deleteById(Long productId);

    long count();

    Page<Product> findAll(Pageable pageable);
}