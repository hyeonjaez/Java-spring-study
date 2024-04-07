package com.nhnacademy.shoppingmall.controller.signUp;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.POST, value = "/signup/checkIdAction.do")
public class SignUpDuplicatedPostController implements BaseController {
    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("user_Id");

        if (userRepository.countByUserId(userId) < 1) {
            HttpSession session = req.getSession();
            session.setAttribute("user_Id", userId);
            req.setAttribute("isDuplicate", false);
        } else {
            req.setAttribute("isDuplicate", true);
        }
        return "shop/signup/checkIdResult";
    }
}
