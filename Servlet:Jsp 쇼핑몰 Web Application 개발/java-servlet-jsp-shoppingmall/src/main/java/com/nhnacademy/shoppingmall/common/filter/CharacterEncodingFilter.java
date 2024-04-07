package com.nhnacademy.shoppingmall.common.filter;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class CharacterEncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");

        if (Objects.isNull(encoding)) {
            this.encoding = "UTF-8";
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding(this.encoding);
        servletResponse.setCharacterEncoding(this.encoding);

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
