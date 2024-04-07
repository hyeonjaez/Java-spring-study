package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/user.do")
public class AdminUserManagement implements BaseController {
    UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private static final int DEFAULT_PAGE_NUMBER = 2;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String pageNumber = req.getParameter("page");
        Page<User> page;
        if (Objects.isNull(pageNumber)) {
            page = userService.userPage(1, DEFAULT_PAGE_NUMBER);
            req.setAttribute("page", 1);
        } else {
            page = userService.userPage(Integer.parseInt(pageNumber), DEFAULT_PAGE_NUMBER);
            req.setAttribute("page", page);
        }

        req.setAttribute("pageUser", page);
        return "admin/adminUser";
    }
}
