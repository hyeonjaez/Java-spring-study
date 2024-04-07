package com.nhnacademy.shoppingmall.controller.signUp;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.domain.UserAddress;
import com.nhnacademy.shoppingmall.user.repository.impl.UserAddressRepositoryImpl;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserAddressService;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserAddressServiceImpl;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/signupAction.do")
public class SignUpPostController implements BaseController {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private final UserAddressService userAddressService = new UserAddressServiceImpl(new UserAddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String getId = req.getParameter("user_Id");
        String getName = req.getParameter("username");
        String getPassword = req.getParameter("password");
        String getBirth = req.getParameter("birth");
        String[] getAddress = req.getParameterValues("addresses[]");
        User.Auth auth = User.Auth.ROLE_USER;
        int initialPoint = 1000000;

        User user = new User(getId, getName, getPassword, getBirth, auth, initialPoint, LocalDateTime.now(), null);
        userService.saveUser(user);
        for (String addressName : getAddress) {
            userAddressService.saveUserAddress(new UserAddress(0, user.getUserId(), addressName));
        }
        return "redirect:/login.do";
    }
}
