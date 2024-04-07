package com.nhnacademy.shoppingmall.check.user.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Slf4j
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class UserRepositoryImplTest {
    UserRepository userRepository = new UserRepositoryImpl();

    User testUser;

    @BeforeEach
    void setUp() throws SQLException {
        DbConnectionThreadLocal.initialize();
        testUser =
                new User("nhnacademy-test-user", "nhn아카데미", "nhnacademy-test-password", "19900505", User.Auth.ROLE_USER,
                        100_0000, LocalDateTime.now(), null);
        userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() throws SQLException {
        DbConnectionThreadLocal.setSqlError(true);
        DbConnectionThreadLocal.reset();
    }

    @Test
    @Order(1)
    @DisplayName("로그인: user 조회 by userId and userPassword")
    void findByUserIdAndUserPassword() {
        Optional<User> userOptional =
                userRepository.findByUserIdAndUserPassword(testUser.getUserId(), testUser.getUserPassword());
        Assertions.assertTrue(userOptional.isPresent());
        Assertions.assertEquals(testUser, userOptional.get());
    }

    @Test
    @Order(2)
    @DisplayName("로그인 : sql injection 방어")
    @Disabled
    void findByUserIdAndUserPassword_sql_injection() {
        //테스트 코드가 통과할 수 있도록  userRepository.findByUserIdAndUserPassword를 수정하세요.
        String password = "' or '1'='1";
        Optional<User> userOptional = userRepository.findByUserIdAndUserPassword(testUser.getUserId(), password);
        log.debug("user:{}", userOptional.orElse(null));
        Assertions.assertFalse(userOptional.isPresent());
    }

    @Test
    @Order(3)
    @DisplayName("user 조회 by uerId")
    void findById() {
        Optional<User> userOptional = userRepository.findById(testUser.getUserId());
        Assertions.assertTrue(userOptional.isPresent());
        Assertions.assertEquals(testUser, userOptional.get());
    }

    @Test
    @Order(4)
    @DisplayName("user 등록")
    void save() {
        User newUser = new User("nhnacademy-test-user2", "nhn아카데미2", "nhnacademy-test-password2", "19900502",
                User.Auth.ROLE_USER, 100_0000, LocalDateTime.now(), null);
        int result = userRepository.save(newUser);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, result),
                () -> Assertions.assertEquals(newUser, userRepository.findById(newUser.getUserId()).get())
        );
    }

    @Test
    @Order(5)
    @DisplayName("user 중복 등록 - 제약조건 확인")
    void save_duplicate_user_id() {

        Throwable throwable = Assertions.assertThrows(RuntimeException.class, () -> {
            userRepository.save(testUser);
        });
        Assertions.assertTrue(
                throwable.getMessage().contains(SQLIntegrityConstraintViolationException.class.getName()));
        log.debug("errorMessage:{}", throwable.getMessage());
    }

    @Test
    @Order(6)
    @DisplayName("user 삭제")
    void deleteByUserId() {
        int result = userRepository.deleteByUserId(testUser.getUserId());
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, result),
                () -> Assertions.assertFalse(userRepository.findById(testUser.getUserId()).isPresent())
        );
    }

    @Test
    @Order(7)
    @DisplayName("user 수정")
    void update() {
        testUser.setUserName("nhn아카데미");
        testUser.setUserAuth(User.Auth.ROLE_ADMIN);
        testUser.setUserBirth("20100505");
        testUser.setUserPoint(20_0000);
        testUser.setUserPassword("new-password");

        int result = userRepository.update(testUser);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, result),
                () -> Assertions.assertEquals(testUser, userRepository.findById(testUser.getUserId()).get())
        );
    }

    @Test
    @Order(8)
    @DisplayName("최근 로그인시간 update")
    void updateLatestLoginAtByUserId() {
        int result = userRepository.updateLatestLoginAtByUserId(testUser.getUserId(), LocalDateTime.now());
        Assertions.assertEquals(1, result);
    }
}