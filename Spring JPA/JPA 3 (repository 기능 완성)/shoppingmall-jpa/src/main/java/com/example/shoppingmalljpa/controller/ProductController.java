package com.example.shoppingmalljpa.controller;

import com.example.shoppingmalljpa.domain.ProductDto;
import com.example.shoppingmalljpa.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String viewProduct(@RequestParam(name = "page", defaultValue = "1") int pageNumber,
                              @RequestParam(name = "size", defaultValue = "5") int pageSize,
                              Model model) {

        Page<ProductDto> productPage = productService.findAllProducts(pageNumber, pageSize);
        model.addAttribute("productPage", productPage);

        return "index";
    }

    @GetMapping("/{productId}")
    public String viewProductDetail(@PathVariable Long productId, Model model) {

        ProductDto productDto = productService.findById(productId);
        ProductDto.ProductDetailDto productDetailDto = productService.findDetailById(productId);

        model.addAttribute("productDto", productDto);
        model.addAttribute("productDetailDto", productDetailDto);
        return "productDetail";
    }

    @PostMapping("/{productId}/delete")
    public String delete(@PathVariable Long productId) {
        productService.deleteById(productId);
        return "redirect:/";
    }
}
