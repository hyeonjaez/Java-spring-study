package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.util.List;

public interface ProductService {

    List<Product> getTotalProduct();

    Product getProduct(int productId);

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int productId);

    Page<Product> pageFindAll(int page, int pageSize);

    int getNextProductId();

    Page<Product> categoryProductPage(int categoryId, int page, int pageSize);
}
