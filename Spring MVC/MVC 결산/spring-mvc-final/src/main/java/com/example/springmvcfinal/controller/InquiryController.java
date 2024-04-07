package com.example.springmvcfinal.controller;

import com.example.springmvcfinal.domain.Inquiry;
import com.example.springmvcfinal.domain.InquiryCategory;
import com.example.springmvcfinal.repository.InquiryRepository;
import com.example.springmvcfinal.repository.UserInquiryRepository;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class InquiryController {
    private final InquiryRepository inquiryRepository;
    private final UserInquiryRepository userInquiryRepository;

    public InquiryController(InquiryRepository inquiryRepository, UserInquiryRepository userInquiryRepository) {
        this.inquiryRepository = inquiryRepository;
        this.userInquiryRepository = userInquiryRepository;
    }

    @GetMapping("/cs/inquiry-details")
    public String inquiryDetail(@RequestParam("inquiryId") long inquiryId,
                                Model model) {
        Inquiry inquiry = inquiryRepository.getInquiryList(inquiryId);
        model.addAttribute("inquiry", inquiry);
        return "inquiry_detail";
    }

    @GetMapping("/cs/inquiry")
    public String inquiry() {

        return "inquiryForm";
    }

    @PostMapping("/cs/inquiry")
    public String doInquiry(@RequestParam("title") String title,
                            @RequestParam("category") String category,
                            @RequestParam("content") String content,
                            @RequestParam("file") List<MultipartFile> multipartFiles,
                            HttpSession session) {
//        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        InquiryCategory inquiryCategory = InquiryCategory.fromString(category);
        Inquiry inquiry = inquiryRepository.register(title, inquiryCategory, content, LocalDateTime.now());

        List<File> fileList = mapToFile(multipartFiles);

        if (!fileList.isEmpty()) {
            inquiryRepository.setFiles(inquiry.getId(), fileList);
        }
        userInquiryRepository.addInquiry(userId, inquiry.getId());
        return "redirect:/cs?id=" + userId;
    }

    private List<File> mapToFile(List<MultipartFile> files) {
        List<File> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    String fileName = file.getOriginalFilename();
                    String fileExtension = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf(".") + 1);
                    String uploadDirectory = "/Users/hyeon/Desktop/image/";

                    String filePath = uploadDirectory + UUID.randomUUID() + "." + fileExtension;
                    File savedFile = new File(filePath);
                    file.transferTo(savedFile);
                    System.out.println("file name ");
                    System.out.println(savedFile.getName());
                    fileList.add(savedFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return fileList;
    }


}