package com.nhnacademy.shoppingmall.controller.shoppingcart;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.shoppingcart.domain.ShoppingCart;
import com.nhnacademy.shoppingmall.shoppingcart.repository.impl.ShoppingCartRepositoryImpl;
import com.nhnacademy.shoppingmall.shoppingcart.service.ShoppingCartService;
import com.nhnacademy.shoppingmall.shoppingcart.service.impl.ShoppingCartServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequestMapping(method = RequestMapping.Method.POST, value = "/shopping/productDelete.do")
public class ShoppingCartDeleteController implements BaseController {

    private final ShoppingCartService shoppingCartService =
            new ShoppingCartServiceImpl(new ShoppingCartRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String productId = req.getParameter("productId");
        User user = (User) req.getSession().getAttribute("user");

        if (Objects.nonNull(productId) && Objects.nonNull(user)) {
            shoppingCartService.deleteShoppingCart(new ShoppingCart(productId, user.getUserId(), 0));
        }

        return "redirect:/shopping/cart.do";
    }
}
