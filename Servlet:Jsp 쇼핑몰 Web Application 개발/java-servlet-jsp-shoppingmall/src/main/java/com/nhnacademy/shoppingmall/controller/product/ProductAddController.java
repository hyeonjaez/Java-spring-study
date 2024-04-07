package com.nhnacademy.shoppingmall.controller.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.CategoryService;
import com.nhnacademy.shoppingmall.product.service.impl.CategoryServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/productAdd.do")
public class ProductAddController implements BaseController {
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("categoryList", categoryService.categoryList());
        return "admin/adminProductAdd";
    }
}
