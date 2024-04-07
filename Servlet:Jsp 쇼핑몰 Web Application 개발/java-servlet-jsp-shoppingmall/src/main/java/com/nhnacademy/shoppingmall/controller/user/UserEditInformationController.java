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

@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/editUserInformation.do")
public class UserEditInformationController implements BaseController {
    private final UserAddressService userAddressService = new UserAddressServiceImpl(new UserAddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession(false).getAttribute("user");

        if (Objects.nonNull(user)) {
            request.setAttribute("userId", user.getUserId());
            request.setAttribute("userName", user.getUserName());
            request.setAttribute("userPassword", user.getUserPassword());
            request.setAttribute("address", userAddressService.getUserAddressBy(user.getUserId()));
            return "shop/user/mypageEdit";
        } else {
            return "redirect:/index.do";
        }
    }

}
