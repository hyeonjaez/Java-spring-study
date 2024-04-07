package com.example.springmvcfinal.controller;

import com.example.springmvcfinal.domain.Answer;
import com.example.springmvcfinal.domain.Inquiry;
import com.example.springmvcfinal.repository.InquiryRepository;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cs/admin")
public class AdminController {

    private final InquiryRepository inquiryRepository;

    public AdminController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    @GetMapping
    public String csAdminMain(Model model) {

        List<Inquiry> notAnsweredInquiryList = inquiryRepository.notAnsweredInquiryList();
        model.addAttribute("notAnsweredInquiryList", notAnsweredInquiryList);

        return "admin_main";
    }

    @GetMapping("/answer")
    public String answerAdmin(@RequestParam("inquiry") long inquiryId, Model model) {
        Inquiry inquiry = inquiryRepository.getInquiryList(inquiryId);
        model.addAttribute("inquiry", inquiry);
        return "answerForm";
    }

    @PostMapping("/answer")
    public String answerForm(@RequestParam("inquiryId") long inquiryId, @RequestParam("content") String content,
                             HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        Answer answer = new Answer(content, LocalDateTime.now(), userId);
        inquiryRepository.setAnswer(answer, inquiryId);
        return "redirect:/cs/admin";
    }
}