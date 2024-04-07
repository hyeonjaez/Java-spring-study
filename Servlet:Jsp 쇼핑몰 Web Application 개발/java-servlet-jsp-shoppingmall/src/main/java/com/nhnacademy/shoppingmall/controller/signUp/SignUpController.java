package com.nhnacademy.shoppingmall.controller.signUp;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.GET, value = "/signup.do")
public class SignUpController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);

        if (Objects.nonNull(session) && Objects.nonNull(session.getAttribute("user_id"))) {
            return "redirect:/index.do";
        }

        return "shop/signup/signup";
    }
}
