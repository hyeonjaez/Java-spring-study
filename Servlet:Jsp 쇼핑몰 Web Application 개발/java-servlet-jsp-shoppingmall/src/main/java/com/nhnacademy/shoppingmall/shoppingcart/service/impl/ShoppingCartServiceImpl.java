package com.nhnacademy.shoppingmall.shoppingcart.service.impl;

import com.nhnacademy.shoppingmall.shoppingcart.domain.ShoppingCart;
import com.nhnacademy.shoppingmall.shoppingcart.repository.ShoppingCartRepository;
import com.nhnacademy.shoppingmall.shoppingcart.service.ShoppingCartService;
import java.util.List;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public List<ShoppingCart> getShoppingCartList(String userId) {
        return this.shoppingCartRepository.getShoppingCart(userId);
    }

    @Override
    public void saveShoppingCart(ShoppingCart shoppingCart) {
        if (this.shoppingCartRepository.countShoppingCart(shoppingCart) < 1) {
            this.shoppingCartRepository.saveShoppingCart(shoppingCart);
        }
    }

    @Override
    public void deleteShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCartRepository.deleteShoppingCart(shoppingCart);
    }

    @Override
    public void updateShoppingCartAmount(ShoppingCart shoppingCart) {
        this.shoppingCartRepository.updateShoppingCartAmount(shoppingCart);
    }
}
