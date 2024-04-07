package com.nhnacademy.shoppingmall.controller.shoppingcart;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.shoppingcart.domain.ShoppingCart;
import com.nhnacademy.shoppingmall.shoppingcart.repository.impl.ShoppingCartRepositoryImpl;
import com.nhnacademy.shoppingmall.shoppingcart.service.ShoppingCartService;
import com.nhnacademy.shoppingmall.shoppingcart.service.impl.ShoppingCartServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/shoppingcart/addProduct.do")
public class ShoppingCartAddController implements BaseController {
    private final ShoppingCartService shoppingCartService =
            new ShoppingCartServiceImpl(new ShoppingCartRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession(false).getAttribute("user");
        String productId = req.getParameter("productId");
        shoppingCartService.saveShoppingCart(new ShoppingCart(productId, user.getUserId(), 1));

        return "redirect:/shopping/cart.do";
    }
}
