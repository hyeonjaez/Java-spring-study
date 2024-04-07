package com.nhnacademy.shoppingmall.shoppingcart.repository;

import com.nhnacademy.shoppingmall.shoppingcart.domain.ShoppingCart;
import java.util.List;

public interface ShoppingCartRepository {
    List<ShoppingCart> getShoppingCart(String userId);

    int saveShoppingCart(ShoppingCart shoppingCart);

    int deleteShoppingCart(ShoppingCart shoppingCart);

    int updateShoppingCartAmount(ShoppingCart shoppingCart);

    int countShoppingCart(ShoppingCart shoppingCart);
}
