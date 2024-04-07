package com.example.springmvcfinal.repository;

import com.example.springmvcfinal.domain.Inquiry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class UserInquiryRepositoryImpl implements UserInquiryRepository {
    InquiryRepository inquiryRepository;
    Map<String, List<Long>> userInquiryIdMap;


    public UserInquiryRepositoryImpl(InquiryRepository inquiryRepository, Map<String, List<Long>> userInquiryIdMap) {
        this.inquiryRepository = inquiryRepository;
        this.userInquiryIdMap = userInquiryIdMap;
    }

    @Override
    public List<Inquiry> getInquiryList(String userId) {
        List<Long> inquiryIds = userInquiryIdMap.getOrDefault(userId, new ArrayList<>());
        List<Inquiry> inquiryList = new ArrayList<>();

        for (Long inquiryId : inquiryIds) {
            Inquiry inquiry = inquiryRepository.getInquiryList(inquiryId);
            if (Objects.nonNull(inquiry)) {
                inquiryList.add(inquiry);
            }
        }
        return inquiryList;
    }

    public void sortInquiryList(List<Inquiry> inquiryList) {
        if(!inquiryList.isEmpty()){
            inquiryList.sort(Comparator.comparing(Inquiry::getCreatedAt).reversed());
        }

    }

    @Override
    public void addInquiry(String userId, long inquiryId) {
        List<Long> inquiryIdList =
                this.userInquiryIdMap.getOrDefault(userId, new ArrayList<>());
        inquiryIdList.add(inquiryId);
        userInquiryIdMap.put(userId, inquiryIdList);
    }

    @Override
    public List<Inquiry> getInquiryListByCategory(String userId, String category) {
        return getInquiryList(userId).stream()
                .filter(inquiry ->
                        Objects.equals(inquiry.getInquiryCategory().getValue(), category))
                .collect(Collectors.toList());
    }
}
