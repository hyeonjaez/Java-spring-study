package com.nhnacademy.shoppingmall.controller.user;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserAddressRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserAddressService;
import com.nhnacademy.shoppingmall.user.service.impl.UserAddressServiceImpl;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage.do")
public class UserMyPageController implements BaseController {
    private final UserAddressService userAddressService = new UserAddressServiceImpl(new UserAddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession(false).getAttribute("user");
        if (Objects.nonNull(user)) {
            req.setAttribute("userId", user.getUserId());
            req.setAttribute("userName", user.getUserName());
            req.setAttribute("userPassword", user.getUserPassword());
            req.setAttribute("userBirth", user.getUserBirth());
            req.setAttribute("userAuth", user.getUserAuth().name());
            req.setAttribute("userPoint", user.getUserPoint());
            req.setAttribute("createdAt", user.getCreatedAt());
            req.setAttribute("latestLoginAt", user.getLatestLoginAt());
            req.setAttribute("address", userAddressService.getUserAddressBy(user.getUserId()));

        } else {
            return "redirect:/login.do";
        }
        return "shop/user/mypage";
    }
}