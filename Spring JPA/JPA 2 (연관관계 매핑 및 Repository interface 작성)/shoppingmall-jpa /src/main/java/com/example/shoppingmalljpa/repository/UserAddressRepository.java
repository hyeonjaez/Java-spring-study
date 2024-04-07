package com.example.shoppingmalljpa.repository;

import com.example.shoppingmalljpa.domain.UserAddress;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    List<UserAddress> findByAddressName(String addressName);

    void deleteByAddressName(String addressName);
}