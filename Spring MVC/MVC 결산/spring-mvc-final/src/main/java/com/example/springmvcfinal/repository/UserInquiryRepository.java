package com.example.springmvcfinal.repository;

import com.example.springmvcfinal.domain.Inquiry;
import java.util.List;

public interface UserInquiryRepository {
    List<Inquiry>  getInquiryList(String userId);

    void addInquiry(String userId, long inquiryId);

    List<Inquiry> getInquiryListByCategory(String userId, String category);
    void sortInquiryList(List<Inquiry> inquiryList);
}
