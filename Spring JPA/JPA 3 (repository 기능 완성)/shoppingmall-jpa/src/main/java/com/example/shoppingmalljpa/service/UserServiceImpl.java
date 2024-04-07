package com.example.shoppingmalljpa.service;

import com.example.shoppingmalljpa.entity.User;
import com.example.shoppingmalljpa.entity.UserAddress;
import com.example.shoppingmalljpa.repository.UserAddressRepository;
import com.example.shoppingmalljpa.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;

    public UserServiceImpl(UserRepository userRepository, UserAddressRepository userAddressRepository) {
        this.userRepository = userRepository;
        this.userAddressRepository = userAddressRepository;
    }

    @Override
    public User getUser(String userId) {
        Optional<User> userOptional = userRepository.findByUserId(userId);

        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }


    @Override
    public void deleteByPk_AddressIdAndPk_UserId(Long addressId, String userId) {
        userAddressRepository.deleteByPk_AddressIdAndPk_UserId(addressId, userId);
    }

    @Override
    public UserAddress findUserAddressByPk_AddressIdAndPk_UserId(Long addressId, String userId) {
        Optional<UserAddress> optionalUserAddress =
                userAddressRepository.findUserAddressByPk_AddressIdAndPk_UserId(addressId, userId);

        return optionalUserAddress.orElse(null);

    }

    @Override
    public List<UserAddress> findAllByUser(User user) {
        return userAddressRepository.findAllByUser(user);
    }
}
