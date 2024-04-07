package com.example.certificatesystem.controller;

import com.example.certificatesystem.service.ResidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    ResidentService residentService;

    public IndexController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("residentPage", residentService.getResidentsPage());

        return "index";

    }


}
