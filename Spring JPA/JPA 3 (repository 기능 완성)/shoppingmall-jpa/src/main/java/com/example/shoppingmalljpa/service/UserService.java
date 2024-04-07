package com.example.shoppingmalljpa.service;

import com.example.shoppingmalljpa.entity.User;
import com.example.shoppingmalljpa.entity.UserAddress;
import java.util.List;


public interface UserService {
    User getUser(String userId);

    List<UserAddress> findAllByUser(User user);


    void deleteByPk_AddressIdAndPk_UserId(Long addressId, String userId);

    UserAddress findUserAddressByPk_AddressIdAndPk_UserId(Long addressId, String userId);
}
