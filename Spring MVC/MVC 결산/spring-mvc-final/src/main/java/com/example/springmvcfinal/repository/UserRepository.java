package com.example.springmvcfinal.repository;

import com.example.springmvcfinal.domain.User;
import com.example.springmvcfinal.domain.UserCategory;

public interface UserRepository {
    boolean exists(String id);
    boolean matches(String id, String password);
    User register(String id, String password, String name, UserCategory userCategory);
    User getUser(String id);
}
