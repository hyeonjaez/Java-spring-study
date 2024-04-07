package com.nhnacademy.shoppingmall.controller.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Category;
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
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/productInformation.do")
public class ProductInformationController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final CategoryProductService categoryProductService =
            new CategoryProductServiceImpl(new CategoryProductRepositoryImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId = Integer.parseInt(req.getParameter("productId"));
        Product product = productService.getProduct(productId);
        req.setAttribute("product", product);

        List<Integer> categoryIdList = categoryProductService.findCategoryIdByProductId(productId);
        List<Category> categoryList = categoryIdList.stream()
                .map(categoryService::findCategoryById)
                .collect(Collectors.toList());

        req.setAttribute("categoryList", categoryList);

        return "product/productInformation";
    }
}
