package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class ApplicationListener implements ServletContextListener {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            DbConnectionThreadLocal.initialize();
            ServletContext context = sce.getServletContext();

            User admin = new User("admin", "관리자", "12345", "19990808", User.Auth.ROLE_ADMIN, 1000000,
                    LocalDateTime.now(),
                    null);
            User user = new User("user", "사용자", "12345", "19891225", User.Auth.ROLE_USER, 1000000,
                    LocalDateTime.now(),
                    null);
            userService.saveUser(user);
            userService.saveUser(admin);
            context.setAttribute("userService", userService);
        } catch (Exception e) {
            DbConnectionThreadLocal.setSqlError(true);
        } finally {
            DbConnectionThreadLocal.reset();
        }

    }
}
