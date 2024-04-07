package com.example.shoppingmalljpa.service;

import com.example.shoppingmalljpa.domain.ProductDto;
import com.example.shoppingmalljpa.entity.Category;
import com.example.shoppingmalljpa.entity.Product;
import com.example.shoppingmalljpa.repository.CategoryProductRepository;
import com.example.shoppingmalljpa.repository.CategoryRepository;
import com.example.shoppingmalljpa.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    CategoryProductRepository categoryProductRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                              CategoryProductRepository categoryProductRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.categoryProductRepository = categoryProductRepository;
    }

    @Override
    public ProductDto findById(Long productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public ProductDto.ProductDetailDto findDetailById(Long productId) {
        return productRepository.findDetailByProductId(productId);
    }

    @Override
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Page<ProductDto> findAllProducts(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);


        return productRepository
                .findAll(pageRequest)
                .map(this::mapToProductDto);
    }

    private ProductDto mapToProductDto(Product product) {
        return new ProductDto() {

            @Override
            public Long getProductId() {
                return product.getProductId();
            }

            @Override
            public String getModelName() {
                return product.getModelName();
            }

            @Override
            public String getProductImage() {
                return product.getProductImage();
            }

            @Override
            public BigDecimal getUnitCost() {
                return product.getUnitCost();
            }
        };
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Category> findAllCategory() {

        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public List<Product> productListByCategory(Long categoryId) {
//        return categoryProductRepository.findProductCategoryByCategory_CategoryId(categoryId)
//                .stream()
//                .map(ProductCategory::getProduct)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<Category> categoryListByProduct(Long productId) {
//        return categoryProductRepository.findProductCategoryByProduct_ProductId(productId)
//                .stream()
//                .map(ProductCategory::getCategory)
//                .collect(Collectors.toList());
        return null;
    }
}