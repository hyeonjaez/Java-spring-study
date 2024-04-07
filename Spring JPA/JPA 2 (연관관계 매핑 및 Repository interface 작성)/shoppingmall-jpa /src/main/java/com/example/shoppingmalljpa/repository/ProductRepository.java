package com.example.shoppingmalljpa.repository;

import com.example.shoppingmalljpa.domain.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long productId);

//    Optional<Product> save(Product product);


    void deleteById(Long productId);

    long count();

    List<Product> findAll();

    long countByProductId(Long productId);

    int getLastIdNumber();

    Page<Product> findByCategoryId(int categoryId, Pageable pageable);

    int countByCategoryId(int categoryId);
}