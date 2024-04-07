//package com.nhnacademy.shoppingmall.controller.index;
//
//import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
//import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
//import com.nhnacademy.shoppingmall.common.page.Page;
//import com.nhnacademy.shoppingmall.product.domain.Category;
//import com.nhnacademy.shoppingmall.product.domain.Product;
//import com.nhnacademy.shoppingmall.product.repository.impl.CategoryProductRepositoryImpl;
//import com.nhnacademy.shoppingmall.product.repository.impl.CategoryRepositoryImpl;
//import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
//import com.nhnacademy.shoppingmall.product.service.CategoryProductService;
//import com.nhnacademy.shoppingmall.product.service.CategoryService;
//import com.nhnacademy.shoppingmall.product.service.ProductService;
//import com.nhnacademy.shoppingmall.product.service.impl.CategoryProductServiceImpl;
//import com.nhnacademy.shoppingmall.product.service.impl.CategoryServiceImpl;
//import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
//import java.util.List;
//import java.util.Objects;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@RequestMapping(method = RequestMapping.Method.GET, value = {"/page.do"})
//public class ProductListController implements BaseController {
//
//    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
//    private final CategoryProductService categoryProductService =
//            new CategoryProductServiceImpl(new CategoryProductRepositoryImpl());
//    private static final int DEFAULT_PAGE_NUMBER = 2;
//    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        int categoryId = parseCategoryId(request.getParameter("categoryNumber"));
//        int pageNumber = parsePageNumber(request.getParameter("pageNumber"));
//
//        List<Category> categoryList = categoryService.categoryList();
//        Page<Product> productPage = productService.categoryProductPage(categoryId, pageNumber, DEFAULT_PAGE_NUMBER);
//
//        request.setAttribute("categoryNumber", categoryId);
//        request.setAttribute("pageNumber", pageNumber);
//        request.setAttribute("productPage", productPage);
//        request.setAttribute("categoryList", categoryList);
//
//        return "/shop/main/test";
//    }
//
//    private int parseCategoryId(String categoryId) {
//        if (Objects.isNull(categoryId)) {
//            return 1;
//        }
//        return Integer.parseInt(categoryId);
//    }
//
//    private int parsePageNumber(String pageNumber) {
//        if (Objects.isNull(pageNumber)) {
//            return 1;
//        }
//        return Integer.parseInt(pageNumber);
//    }
//}