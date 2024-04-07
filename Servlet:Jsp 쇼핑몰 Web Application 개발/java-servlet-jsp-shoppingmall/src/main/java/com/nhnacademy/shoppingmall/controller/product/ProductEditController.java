package com.nhnacademy.shoppingmall.controller.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.CategoryProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.CategoryProductService;
import com.nhnacademy.shoppingmall.product.service.CategoryService;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.CategoryProductServiceImpl;
import com.nhnacademy.shoppingmall.product.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/productEdit.do")
public class ProductEditController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final CategoryProductService categoryProductService =
            new CategoryProductServiceImpl(new CategoryProductRepositoryImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int procutId = Integer.parseInt(req.getParameter("productId"));
        Product product = productService.getProduct(procutId);
        HttpSession session = req.getSession();
        session.setAttribute("product", product);
        req.setAttribute("categoryList", categoryService.categoryList());
        req.setAttribute("categoryProductList",
                categoryProductService.findCategoryIdByProductId(product.getProductId()));
        return "admin/admin_product_edit";
    }
}
