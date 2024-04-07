package com.nhnacademy.exam.interceptor;

import com.nhnacademy.exam.exception.HeaderException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    private static final String AUTH_USER_ID = "nhnacademy";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws RuntimeException {
        String userId = request.getHeader("X-USER-ID");

        String accept = request.getContentType();

        if (Objects.isNull(userId) || userId.isEmpty()) {
            throw new HeaderException("Unauthorized");
        }


        if (Objects.equals(userId, AUTH_USER_ID)) {
            return true;
        }

        throw new HeaderException("Unauthorized");
    }
}
