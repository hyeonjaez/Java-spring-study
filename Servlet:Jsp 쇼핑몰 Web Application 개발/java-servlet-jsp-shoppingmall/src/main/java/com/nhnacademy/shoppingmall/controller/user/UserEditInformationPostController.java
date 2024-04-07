package com.nhnacademy.shoppingmall.controller.user;

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
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/mypage/editUserInformationPost.do")
public class UserEditInformationPostController implements BaseController {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private final UserAddressService userAddressService = new UserAddressServiceImpl(new UserAddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        List<String> addresses = Arrays.asList(req.getParameterValues("addressName"));

        userAddressService.deleteAddressByUserId(userId);
        User user = userService.getUser(userId);

        String userBirth = user.getUserBirth();
        User.Auth userAuth = user.getUserAuth();
        int userPoint = user.getUserPoint();
        LocalDateTime createAt = user.getCreatedAt();
        LocalDateTime latestLoginAt = user.getLatestLoginAt();

        User updateUser =
                new User(userId, userName, userPassword, userBirth, userAuth, userPoint, createAt, latestLoginAt);
        userService.updateUser(updateUser);
        req.getSession().setAttribute("user", updateUser);

        for (String addressName : addresses) {
            userAddressService.saveUserAddress(new UserAddress(0, userId, addressName));
        }

        return "redirect:/mypage.do";
    }
}
