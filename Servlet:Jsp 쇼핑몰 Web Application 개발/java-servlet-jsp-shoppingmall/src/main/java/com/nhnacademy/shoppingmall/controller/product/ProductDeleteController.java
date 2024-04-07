package com.nhnacademy.shoppingmall.controller.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.CategoryProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.CategoryProductService;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.CategoryProductServiceImpl;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/productDelete.do")
public class ProductDeleteController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final CategoryProductService categoryProductService =
            new CategoryProductServiceImpl(new CategoryProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Product product = (Product) req.getSession(false).getAttribute("product");
        categoryProductService.deleteCategoryProduct(product.getProductId());
        productService.deleteProduct(product.getProductId());
        return "redirect:/admin/product.do";
    }
}
