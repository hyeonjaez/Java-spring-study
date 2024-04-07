package com.nhnacademy.shoppingmall.controller.shoppingcart;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.shoppingcart.domain.ShoppingCart;
import com.nhnacademy.shoppingmall.shoppingcart.repository.impl.ShoppingCartRepositoryImpl;
import com.nhnacademy.shoppingmall.shoppingcart.service.ShoppingCartService;
import com.nhnacademy.shoppingmall.shoppingcart.service.impl.ShoppingCartServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/shopping/cart.do")
public class ShoppingCartController implements BaseController {
    private final ShoppingCartService shoppingCartService =
            new ShoppingCartServiceImpl(new ShoppingCartRepositoryImpl());
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession(false).getAttribute("user");

        List<ShoppingCart> shoppingCartList =
                shoppingCartService.getShoppingCartList(user.getUserId());
        List<Product> productList = new ArrayList<>();

        shoppingCartList.stream()
                .forEach(cart -> productList.add(productService.getProduct(Integer.parseInt(cart.getProductId()))));


        req.setAttribute("productList", productList);

        return "shop/shopping/cart";
    }
}