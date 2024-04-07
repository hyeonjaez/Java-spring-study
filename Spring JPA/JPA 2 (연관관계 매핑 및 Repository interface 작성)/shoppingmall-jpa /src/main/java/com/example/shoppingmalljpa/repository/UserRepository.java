package com.example.shoppingmalljpa.repository;

import com.example.shoppingmalljpa.domain.User;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserIdAndUserPassword(String userId, String userPassword);

    Optional<User> findByUserId(String userId);

    int deleteByUserId(String userId);

    int countByUserId(String userId);

    int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt);

}