package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin.do")
public class AdminFirstController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        if (Objects.nonNull(session) || Objects.nonNull(user)) {
            if (user.getUserAuth().equals(User.Auth.ROLE_ADMIN)) {
                return "admin/admin";
            }
        }
        return "redirect:/index.do";
    }
}
