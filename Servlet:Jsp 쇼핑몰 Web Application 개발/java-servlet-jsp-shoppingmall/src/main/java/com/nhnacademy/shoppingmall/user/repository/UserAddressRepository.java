package com.nhnacademy.shoppingmall.user.repository;

import com.nhnacademy.shoppingmall.user.domain.UserAddress;
import java.util.List;

public interface UserAddressRepository {
    List<UserAddress> getUserAddressBy(String userId);

    int deleteAddressByUserId(String userId);

    int saveAddress(UserAddress userAddress);

}
