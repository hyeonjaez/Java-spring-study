package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.GET, value = "/logout.do")
public class LogoutController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);

        if (Objects.nonNull(session)) {
            session.removeAttribute("user");
        }

        req.getSession().invalidate();
        return "redirect:/login.do";
    }
}
