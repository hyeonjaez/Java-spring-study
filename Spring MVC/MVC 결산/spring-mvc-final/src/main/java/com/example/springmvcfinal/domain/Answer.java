package com.example.springmvcfinal.domain;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Answer {
    private String content;
    private LocalDateTime answeredAt;
    private String csId;

    public Answer(String content, LocalDateTime answeredAt, String csId) {
        this.content = content;
        this.answeredAt = answeredAt;
        this.csId = csId;
    }
}
