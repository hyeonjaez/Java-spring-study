package com.nhnacademy.shoppingmall.common.filter;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/shopping/*")
public class ShoppingLoginCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        if (Objects.isNull(session) || Objects.isNull(session.getAttribute("user"))) {
            response.sendRedirect("/login.do");
        } else {
            chain.doFilter(request, response);
        }
    }
}
