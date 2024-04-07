package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/loginAction.do")
public class LoginPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setMaxInactiveInterval(60 * 60);
        String userId = req.getParameter("user_id");
        String userPassword = req.getParameter("user_password");
        try {
            userService.doLogin(userId, userPassword);
        } catch (UserNotFoundException e) {
            return "redirect:/login.do";
        }
        User user = userService.getUser(userId);
        req.getSession().setAttribute("user", user);

        if (user.getUserAuth().equals(User.Auth.ROLE_ADMIN)) {
            return "redirect:/admin.do";
        } else {
            return "redirect:/index.do";
        }


    }
}
