package com.example.springmvcfinal.controller;

import java.util.Objects;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String index(HttpSession session) {
        if(Objects.nonNull(session)){
            return "redirect:/cs/logout";
        }
        return "index";
    }
}
