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
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/addProduct.do")

public class ProductAddPostController implements BaseController {

    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR =
            "/Users/hyeon/Desktop/시험과제 /java-servlet-jsp-shoppingmall/src/main/webapp/resources";
    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    CategoryProductService categoryProductService = new CategoryProductServiceImpl(new CategoryProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String modelName = req.getParameter("modelName");
        String modelNumber = req.getParameter("modelNumber");
        int unitCost = Integer.parseInt(req.getParameter("unitCost"));
        String description = req.getParameter("description");
        List<Integer> category = Arrays.stream(req.getParameterValues("category")).map(Integer::parseInt).collect(
                Collectors.toList());

        String productImage = "";
        try {


            for (Part part : req.getParts()) {
                String contentDisposition = part.getHeader(CONTENT_DISPOSITION);

                if (contentDisposition.contains("filename=")) {
                    String fileName = extractFileName(contentDisposition);

                    if (part.getSize() > 0) {
                        part.write(UPLOAD_DIR + File.separator + fileName);
                        productImage = "/resources" + File.separator + fileName;
                        part.delete();
                    } else {
                        productImage = "/resources" + File.separator + "no-image.png";
                    }
                } else {
                    String formValue = req.getParameter(part.getName());
                    log.error("{}={}", part.getName(), formValue);
                }
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Product product = new Product(0, modelName, modelNumber, productImage, unitCost, description);
        productService.saveProduct(product);
        int productId = productService.getNextProductId();
        categoryProductService.addCategoryProduct(category, productId);


        return "redirect:/admin/product.do";
    }

    private String extractFileName(String contentDisposition) {
        log.error("contentDisposition:{}", contentDisposition);
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return null;
    }
}