package com.example.springmvcfinal.controller;

import com.example.springmvcfinal.domain.User;
import com.example.springmvcfinal.domain.UserCategory;
import com.example.springmvcfinal.exception.LoginFailException;
import com.example.springmvcfinal.repository.UserRepository;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/cs/login")
    public String login(@CookieValue(value = "SESSION", required = false) String session, Model model) {
        if (StringUtils.hasText(session)) {
            model.addAttribute("id", session);
            return "redirect:/cs";
        } else {
            return "loginForm";
        }
    }

    @PostMapping("/cs/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("password") String password,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          ModelMap modelMap) {
        if (userRepository.matches(id, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", id);
            session.setMaxInactiveInterval(30 * 60);
            Cookie cookie = new Cookie("SESSION", session.getId());
            cookie.setMaxAge(30 * 60);
            response.addCookie(cookie);

            User user = userRepository.getUser(id);
            modelMap.put("user", user);

            return isAdmin(id, user);
        } else {
            throw new LoginFailException("not match id or password");
        }
    }

    private static String isAdmin(String id, User user) {
        if (Objects.equals(user.getUserCategory(), UserCategory.ADMIN)) {
            return "redirect:/cs/admin";
        } else {
            return "redirect:/cs?id=" + id;
        }
    }

    @GetMapping("/cs/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (Objects.nonNull(session)) {
            session.invalidate();
            Cookie[] cookies = request.getCookies();
            if (Objects.nonNull(cookies)) {
                for (Cookie cookie : cookies) {
                    if ("SESSION".equals(cookie.getName())) {
                        cookie.setValue(null);
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        break;
                    }
                }
            }
        }
        return "redirect:/cs/login";
    }
}
