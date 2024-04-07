package com.example.springmvcfinal.domain;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Inquiry {

    private final long id;
    private final String title;
    private final InquiryCategory inquiryCategory;
    private final String content;
    private final LocalDateTime createdAt;
    private boolean answered;
    private Answer answer;
    @Setter
    private List<File> files;

    public Inquiry(long id, String title, InquiryCategory inquiryCategory, String content, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.inquiryCategory = inquiryCategory;
        this.content = content;
        this.createdAt = createdAt;
        this.answered = false;
    }


    public void addAnswer(Answer answer) {
        if (!answered) {
            this.answered = true;
            this.answer = answer;
        }
    }
}
