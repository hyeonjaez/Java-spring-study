package com.example.springmvcfinal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.springmvcfinal.domain.Inquiry;
import com.example.springmvcfinal.domain.InquiryCategory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UserInquiryRepositoryImplTest {

    private UserInquiryRepository userInquiryRepository;
    private InquiryRepository mockInquiryRepository;
    private Map<String, List<Long>> userInquiryIdMap;

    @BeforeEach
    public void setUp() {
        mockInquiryRepository = mock(InquiryRepository.class);
        userInquiryIdMap = new HashMap<>();
        userInquiryRepository = new UserInquiryRepositoryImpl(mockInquiryRepository, userInquiryIdMap);
    }

    @Test
    void testGetInquiryList() {
        String userId = "userId";
        List<Long> inquiryIds = List.of(1L, 2L, 3L);
        userInquiryIdMap.put(userId, inquiryIds);

        when(mockInquiryRepository.getInquiryList(1L)).thenReturn(
                new Inquiry(1L, "title1", InquiryCategory.COMPLAINT_SUBMISSION, "content1", null));
        when(mockInquiryRepository.getInquiryList(2L)).thenReturn(
                new Inquiry(2L, "title2", InquiryCategory.PROPOSAL, "content2", null));

        List<Inquiry> resultList = userInquiryRepository.getInquiryList(userId);
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals(1L, resultList.get(0).getId());
        assertEquals(2L, resultList.get(1).getId());
    }

    @Test
    void testAddInquiry() {
        String userId = "userId";
        long inquiryId = 1L;
        userInquiryRepository.addInquiry(userId, inquiryId);
        assertTrue(userInquiryIdMap.containsKey(userId));
        assertTrue(userInquiryIdMap.get(userId).contains(inquiryId));
    }

    @Test
    void testGetInquiryListByCategory() {
        String userId = "userId";
        List<Long> inquiryIds = List.of(1L, 2L, 3L);
        userInquiryIdMap.put(userId, inquiryIds);

        when(mockInquiryRepository.getInquiryList(1L)).thenReturn(
                new Inquiry(1L, "title1", InquiryCategory.COMPLAINT_SUBMISSION, "content1", null));
        when(mockInquiryRepository.getInquiryList(2L)).thenReturn(
                new Inquiry(2L, "title2", InquiryCategory.PROPOSAL, "content2", null));
        when(mockInquiryRepository.getInquiryList(3L)).thenReturn(
                new Inquiry(3L, "title3", InquiryCategory.COMPLAINT_SUBMISSION, "content3", null));

        List<Inquiry> result = userInquiryRepository.getInquiryListByCategory(userId, "불만 접수");

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(3L, result.get(1).getId());
    }
}