package com.nhnacademy.shoppingmall.user.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.service.UserService;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String userId) {
        Optional<User> user = this.userRepository.findById(userId);
        return user.orElse(null);
    }

    @Override
    public void saveUser(User user) {
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("saveUser() input null");
        }
        if (this.userRepository.countByUserId(user.getUserId()) < 1) {
            this.userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException(user.getUserId());
        }

    }

    @Override
    public void updateUser(User user) {
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("updateUser() input null");
        }
        if (this.userRepository.countByUserId(user.getUserId()) == 1) {
            this.userRepository.update(user);
        }
    }

    @Override
    public void deleteUser(String userId) {
        if (Objects.isNull(userId)) {
            throw new IllegalArgumentException("deleteUser() input null");
        }
        if (this.userRepository.countByUserId(userId) == 1) {
            this.userRepository.deleteByUserId(userId);
        }
    }

    @Override
    public User doLogin(String userId, String userPassword) {
        if (Objects.isNull(userId) || Objects.isNull(userPassword)) {
            throw new IllegalArgumentException("doLogin() input null");
        }

        Optional<User> user = this.userRepository.findByUserIdAndUserPassword(userId, userPassword);
        if (user.isPresent()) {
            this.userRepository.updateLatestLoginAtByUserId(userId, LocalDateTime.now());

            return user.get();
        }
        throw new UserNotFoundException("user not exist");


    }

    @Override
    public Page<User> userPage(int page, int pageSize) {
        return this.userRepository.userPage(page, pageSize);

    }

}
