package com.example.springmvcfinal.controller;

import com.example.springmvcfinal.domain.Inquiry;
import com.example.springmvcfinal.repository.UserInquiryRepository;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserInquiryRepository userInquiryRepository;

    public UserController(UserInquiryRepository userInquiryRepository) {
        this.userInquiryRepository = userInquiryRepository;
    }

    @GetMapping("/cs")
    public String csMain(HttpSession session,
                         @RequestParam(name = "category", required = false) String category,
                         Model model) {
        String userId = (String) session.getAttribute("userId");

        List<Inquiry> inquiryList;

        if (Objects.nonNull(category) && !category.isEmpty()) {
            inquiryList = userInquiryRepository.getInquiryListByCategory(userId, category);
            userInquiryRepository.sortInquiryList(inquiryList);
        } else {
            inquiryList = userInquiryRepository.getInquiryList(userId);
            userInquiryRepository.sortInquiryList(inquiryList);
        }

        model.addAttribute("inquiryList", inquiryList);
        return "user_main";
    }
}