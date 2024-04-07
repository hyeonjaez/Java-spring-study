package com.nhnacademy.shoppingmall.common.filter;

import com.nhnacademy.shoppingmall.user.domain.User;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(urlPatterns = "/admin/*")
public class AdminCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (Objects.nonNull(session) || Objects.nonNull(user)) {
            if (user.getUserAuth().equals(User.Auth.ROLE_ADMIN)) {
                chain.doFilter(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            }
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
        }
    }
}
