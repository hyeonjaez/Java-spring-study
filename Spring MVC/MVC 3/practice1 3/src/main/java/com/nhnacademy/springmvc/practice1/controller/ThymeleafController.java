package com.nhnacademy.springmvc.practice1.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {
    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("greeting", "Hello");

        model.addAttribute("html", "this is <b>HTML</b>.");

        List<String> list = Arrays.asList("nhn", "academy", "dooray");
        model.addAttribute("list", list);

        model.addAttribute("choice", 2);

        return "thymeleaf/hello";
    }

}
