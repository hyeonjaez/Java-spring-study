package com.nhnacademy.shoppingmall.shoppingcart.service;

import com.nhnacademy.shoppingmall.shoppingcart.domain.ShoppingCart;
import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> getShoppingCartList(String userId);

    void saveShoppingCart(ShoppingCart shoppingCart);

    void deleteShoppingCart(ShoppingCart shoppingCart);

    void updateShoppingCartAmount(ShoppingCart shoppingCart);
}
