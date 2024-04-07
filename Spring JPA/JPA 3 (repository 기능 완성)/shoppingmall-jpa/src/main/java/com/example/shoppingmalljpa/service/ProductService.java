package com.example.shoppingmalljpa.service;

import com.example.shoppingmalljpa.domain.ProductDto;
import com.example.shoppingmalljpa.entity.Category;
import com.example.shoppingmalljpa.entity.Product;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductDto findById(Long productId);

    ProductDto.ProductDetailDto findDetailById(Long productId);

    void deleteById(Long productId);

    Page<ProductDto> findAllProducts(int pageNumber, int pageSize);

    Product save(Product product);

    List<Category> findAllCategory();

    Category findCategoryById(Long categoryId);

    List<Product> productListByCategory(Long categoryId);

    List<Category> categoryListByProduct(Long productId);

}
