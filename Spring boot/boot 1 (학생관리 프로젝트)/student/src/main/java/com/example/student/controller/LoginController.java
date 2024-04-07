package com.example.student.controller;

import com.example.student.service.StudentService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final StudentService studentService;

    public LoginController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        return "loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") Long id,
                          @RequestParam("password") String password,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          ModelMap modelMap) {
        if (studentService.matches(id, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("login", id);

            modelMap.put("student", studentService.getStudent(id));
            return "loginSuccess";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/logout")
    public String doLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "loginForm";
    }

}
