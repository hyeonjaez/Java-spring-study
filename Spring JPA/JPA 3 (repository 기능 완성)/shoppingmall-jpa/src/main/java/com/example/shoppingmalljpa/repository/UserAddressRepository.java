package com.example.shoppingmalljpa.repository;

import com.example.shoppingmalljpa.entity.User;
import com.example.shoppingmalljpa.entity.UserAddress;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository {

    Optional<UserAddress> findUserAddressByPk_AddressIdAndPk_UserId(Long addressId, String userId);

    List<UserAddress> findAllByUser(User user);

    void deleteByPk_AddressIdAndPk_UserId(Long addressId, String userId);

}
