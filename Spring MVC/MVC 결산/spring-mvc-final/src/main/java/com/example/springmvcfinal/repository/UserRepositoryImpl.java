package com.example.springmvcfinal.repository;

import com.example.springmvcfinal.domain.User;
import com.example.springmvcfinal.domain.UserCategory;
import com.example.springmvcfinal.exception.UserAlreadyExistException;
import java.util.Map;
import java.util.Optional;


public class UserRepositoryImpl implements UserRepository {

    private final Map<String, User> userMap;

    public UserRepositoryImpl(Map<String, User> userMap) {
        this.userMap = userMap;
    }

    @Override
    public boolean exists(String id) {
        return userMap.containsKey(id);
    }

    @Override
    public boolean matches(String id, String password) {
        return Optional.ofNullable(getUser(id))
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public User register(String id, String password, String name, UserCategory userCategory) {
        if (exists(id)) {
            throw new UserAlreadyExistException("id는 이미 존재합니다");
        } else {
            User user = new User(id, password, name, userCategory);
            userMap.put(id, user);
            return user;
        }
    }

    @Override
    public User getUser(String id) {
        return exists(id) ? userMap.get(id) : null;
    }
}
