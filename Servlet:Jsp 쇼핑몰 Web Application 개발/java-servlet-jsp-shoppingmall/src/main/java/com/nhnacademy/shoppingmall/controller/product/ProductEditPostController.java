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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/editProduct.do")
public class ProductEditPostController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final CategoryProductService categoryProductService =
            new CategoryProductServiceImpl(new CategoryProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("productId"));
        String modelName = request.getParameter("modelName");
        String modelNumber = request.getParameter("modelNumber");
        String image = request.getParameter("productImage");
        int unitCost = Integer.parseInt(request.getParameter("unitCost"));
        String description = request.getParameter("description");

        Product product = new Product(id, modelNumber, modelName, image, unitCost, description);
        productService.updateProduct(product);
        List<Integer> categoryIdList =
                Arrays.stream(request.getParameterValues("category"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
        categoryProductService.updateCategoryProduct(categoryIdList, product.getProductId());
        return "redirect:/admin/product.do";
    }
}
