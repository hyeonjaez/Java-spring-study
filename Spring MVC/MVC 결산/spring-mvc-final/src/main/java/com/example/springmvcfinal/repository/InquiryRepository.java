package com.example.springmvcfinal.repository;

import com.example.springmvcfinal.domain.Answer;
import com.example.springmvcfinal.domain.Inquiry;
import com.example.springmvcfinal.domain.InquiryCategory;
import com.example.springmvcfinal.domain.User;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

public interface InquiryRepository {
    Inquiry register(String title, InquiryCategory inquiryCategory, String content, LocalDateTime localDateTime);
    Inquiry getInquiryList(long id);
    boolean exists(long id);
    void setFiles(long id, List<File> fileList);
    List<Inquiry> notAnsweredInquiryList();
    void setAnswer(Answer answer, long inquiryId);

}
