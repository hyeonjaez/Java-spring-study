package com.example.springmvcfinal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.springmvcfinal.domain.User;
import com.example.springmvcfinal.domain.UserCategory;
import com.example.springmvcfinal.exception.UserAlreadyExistException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRepositoryImplTest {

    private UserRepository userRepository;
    private Map<String, User> userMap;

    @BeforeEach
    public void setUp() {
        userMap = new HashMap<>();
        userRepository = new UserRepositoryImpl(userMap);
    }

    @Test
    void testExists() {
        String existUserId = "jaehyeon";
        String nonExistUserId = "hi";

        userMap.put(existUserId, new User(existUserId, "password", "jae", UserCategory.USER));

        assertTrue(userRepository.exists(existUserId));
        assertFalse(userRepository.exists(nonExistUserId));
    }

    @Test
    void testMatches() {
        String userId = "testUser";
        String correctPassword = "correctPassword";
        String notCorrectPassword = "incorrectPassword";

        userMap.put(userId, new User(userId, correctPassword, "jae", UserCategory.USER));

        assertTrue(userRepository.matches(userId, correctPassword));
        assertFalse(userRepository.matches(userId, notCorrectPassword));
    }

    @Test
    void testRegister() {
        String userId = "userId";
        String password = "password";
        String name = "name";
        UserCategory userCategory = UserCategory.USER;

        assertNull(userRepository.getUser(userId));

        User registerUser = userRepository.register(userId, password, name, userCategory);

        assertNotNull(registerUser);
        assertEquals(userId, registerUser.getId());
        assertEquals(name, registerUser.getName());
        assertEquals(userCategory, registerUser.getUserCategory());
        assertTrue(userRepository.exists(userId));
        assertThrows(UserAlreadyExistException.class,
                () -> userRepository.register(userId, "", "", UserCategory.ADMIN));
    }

    @Test
    void testGetUser() {
        String userId = "user";
        userMap.put(userId, new User(userId, "password", "jaehyeon", UserCategory.USER));

        User getUser = userRepository.getUser(userId);

        assertNotNull(getUser);
        assertEquals(userId, getUser.getId());
    }

}