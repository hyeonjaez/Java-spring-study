package com.nhnacademy.shoppingmall.user.service.impl;

import com.nhnacademy.shoppingmall.user.domain.UserAddress;
import com.nhnacademy.shoppingmall.user.repository.UserAddressRepository;
import com.nhnacademy.shoppingmall.user.service.UserAddressService;
import java.util.List;

public class UserAddressServiceImpl implements UserAddressService {
    private final UserAddressRepository userAddressRepository;

    public UserAddressServiceImpl(UserAddressRepository userAddressRepository) {
        this.userAddressRepository = userAddressRepository;
    }

    @Override
    public List<UserAddress> getUserAddressBy(String userId) {
        return this.userAddressRepository.getUserAddressBy(userId);
    }

    @Override
    public void deleteAddressByUserId(String userId) {
        this.userAddressRepository.deleteAddressByUserId(userId);
    }

    @Override
    public void saveUserAddress(UserAddress userAddress) {
        this.userAddressRepository.saveAddress(userAddress);
    }
}
