package com.nhnacademy.shoppingmall.user.service;

import com.nhnacademy.shoppingmall.user.domain.UserAddress;
import java.util.List;

public interface UserAddressService {
    List<UserAddress> getUserAddressBy(String userId);

    void deleteAddressByUserId(String userId);

    void saveUserAddress(UserAddress userAddress);
}
