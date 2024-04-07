package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.exception.ProductAlreadyExistsException;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getTotalProduct() {
        return productRepository.findAllProduct();
    }

    @Override
    public Product getProduct(int productId) {
        Optional<Product> getProduct = productRepository.findProductById(productId);
        return getProduct.orElseThrow(() -> new ProductAlreadyExistsException(productId));
    }

    @Override
    public void saveProduct(Product product) {
        if (Objects.isNull(product)) {
            throw new IllegalArgumentException("saveProduct() input null");
        }
        if (this.productRepository.countByProductId(product.getProductId()) < 1) {
            this.productRepository.save(product);
        } else {
            throw new ProductAlreadyExistsException(product.getProductId());
        }

    }

    @Override
    public void updateProduct(Product product) {
        if (Objects.isNull(product)) {
            throw new IllegalArgumentException("updateProduct() input null");
        }
        if (this.productRepository.countByProductId(product.getProductId()) == 1) {
            this.productRepository.update(product);
        }
    }

    @Override
    public void deleteProduct(int productId) {
        if (this.productRepository.countByProductId(productId) == 1) {
            this.productRepository.deleteByProductId(productId);
        }
    }

    @Override
    public Page<Product> pageFindAll(int page, int pageSize) {
        return this.productRepository.findAll(page, pageSize);
    }

    @Override
    public int getNextProductId() {
        return productRepository.getLastIdNumber();
    }

    @Override
    public Page<Product> categoryProductPage(int categoryId, int page, int pageSize) {
        return this.productRepository.findProductByCategory(categoryId, page, pageSize);
    }


}
