//package com.nhnacademy.shoppingmall.controller.index;
//
//import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
//import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
//import com.nhnacademy.shoppingmall.common.page.Page;
//import com.nhnacademy.shoppingmall.product.domain.Product;
//import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
//import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@RequestMapping(method = RequestMapping.Method.GET, value = {"/page.do"})
//public class ProductServlet implements BaseController {
//    private final ProductRepository productRepository = new ProductRepositoryImpl();
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) {
//
//        int defaultPage = 1;
//        int defaultPageSize = 3;
//        int page;
//        int pageSize;
//        Page<Product> prodcutPage;
//        ServletContext context = request.getServletContext();
//        try {
//            page = Integer.parseInt(request.getParameter("pageNumber"));
//            pageSize = Integer.parseInt(request.getParameter("pageSize"));
//            prodcutPage = productRepository.findAll(page, pageSize);
//            request.setAttribute("pageNumber", page);
//            request.setAttribute("pageSize", pageSize);
//        } catch (NumberFormatException e) {
//            prodcutPage = productRepository.findAll(defaultPage, defaultPageSize);
//            request.setAttribute("defaultPageNumber", defaultPage);
//            request.setAttribute("defaultPageSize", defaultPageSize);
//        }
//
//
//        request.setAttribute("productPage", prodcutPage);
//        request.setAttribute("contextPath", context.getContextPath());
//
//
//        return "/shop/main/test";
//    }
//
//}
