package com.example.springmvcfinal.repository;

import com.example.springmvcfinal.domain.Answer;
import com.example.springmvcfinal.domain.Inquiry;
import com.example.springmvcfinal.domain.InquiryCategory;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class InquiryRepositoryImpl implements InquiryRepository {
    private final Map<Long, Inquiry> inquiryUserMap;

    public InquiryRepositoryImpl(Map<Long, Inquiry> inquiryUserMap) {
        this.inquiryUserMap = inquiryUserMap;
    }

    @Override
    public Inquiry register(String title, InquiryCategory inquiryCategory, String content,
                            LocalDateTime localDateTime) {
        long id = currentUserMaxId() + 1;
        Inquiry inquiry = new Inquiry(id, title, inquiryCategory, content, localDateTime);
        inquiryUserMap.put(id, inquiry);
        return inquiry;
    }

    @Override
    public Inquiry getInquiryList(long id) {
        if (exists(id)) {
            return inquiryUserMap.get(id);
        } else {
            return null;
        }
    }



    @Override
    public boolean exists(long id) {
        return inquiryUserMap.containsKey(id);
    }

    @Override
    public void setFiles(long id, List<File> fileList) {
        inquiryUserMap.get(id).setFiles(fileList);
    }

    private long currentUserMaxId() {
        return this.inquiryUserMap.keySet().stream()
                .max(Comparator.comparingInt(Long::intValue))
                .orElse(0L);
    }

    @Override
    public List<Inquiry> notAnsweredInquiryList() {
        return this.inquiryUserMap.values()
                .stream()
                .filter(inquiry -> !inquiry.isAnswered())
                .collect(Collectors.toList());
    }

    @Override
    public void setAnswer(Answer answer, long inquiryId) {
        Inquiry inquiry = inquiryUserMap.get(inquiryId);
        inquiry.addAnswer(answer);
    }


}
