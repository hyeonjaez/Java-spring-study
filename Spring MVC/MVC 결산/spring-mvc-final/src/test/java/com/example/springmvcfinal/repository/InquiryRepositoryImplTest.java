package com.example.springmvcfinal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.springmvcfinal.domain.Answer;
import com.example.springmvcfinal.domain.Inquiry;
import com.example.springmvcfinal.domain.InquiryCategory;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InquiryRepositoryImplTest {

    private InquiryRepository inquiryRepository;
    private Map<Long, Inquiry> inquiryUserMap;

    @BeforeEach
    public void setUp() {
        inquiryUserMap = new HashMap<>();
        inquiryRepository = new InquiryRepositoryImpl(inquiryUserMap);
    }

    @Test
    void testRegister() {
        String title = "title";
        InquiryCategory category = InquiryCategory.COMPLAINT_SUBMISSION;
        String content = "content";
        LocalDateTime dateTime = LocalDateTime.now();

        Inquiry registerInquiry = inquiryRepository.register(title, category, content, dateTime);

        assertNotNull(registerInquiry);
        assertEquals(title, registerInquiry.getTitle());
        assertEquals(category, registerInquiry.getInquiryCategory());
        assertEquals(content, registerInquiry.getContent());
        assertEquals(dateTime, registerInquiry.getCreatedAt());
        assertTrue(inquiryRepository.exists(registerInquiry.getId()));
    }

    @Test
    void testGetInquiryList() {
        long id = 1L;
        Inquiry testInquiry =
                new Inquiry(id, "title", InquiryCategory.COMPLAINT_SUBMISSION, "content", LocalDateTime.now());
        inquiryUserMap.put(id, testInquiry);

        Inquiry getInquiry = inquiryRepository.getInquiryList(id);

        assertNotNull(getInquiry);
        assertEquals(testInquiry, getInquiry);
    }

    @Test
    void testExists() {
        long id = 1L;
        assertFalse(inquiryRepository.exists(id));

        Inquiry testInquiry =
                new Inquiry(id, "title", InquiryCategory.COMPLAINT_SUBMISSION, "content", LocalDateTime.now());
        inquiryUserMap.put(id, testInquiry);

        assertTrue(inquiryRepository.exists(id));
    }

    @Test
    void testSetFiles() {
        long id = 1L;
        Inquiry testInquiry =
                new Inquiry(id, "title", InquiryCategory.COMPLAINT_SUBMISSION, "content", LocalDateTime.now());
        inquiryUserMap.put(id, testInquiry);

        List<File> fileList = new ArrayList<>();
        File testFile = new File("test.txt");
        fileList.add(testFile);

        inquiryRepository.setFiles(id, fileList);

        assertEquals(fileList, testInquiry.getFiles());
    }

    @Test
    void testNotAnsweredInquiryList() {
        Inquiry answeredInquiry =
                new Inquiry(1L, "title", InquiryCategory.COMPLAINT_SUBMISSION, "content", LocalDateTime.now());
        answeredInquiry.addAnswer(new Answer("content", LocalDateTime.now(), "csId"));
        inquiryUserMap.put(answeredInquiry.getId(), answeredInquiry);

        Inquiry notAnsweredInquiry = new Inquiry(2L, "title", InquiryCategory.PROPOSAL, "Content", LocalDateTime.now());
        inquiryUserMap.put(notAnsweredInquiry.getId(), notAnsweredInquiry);

        List<Inquiry> notAnsweredInquiryList = inquiryRepository.notAnsweredInquiryList();

        assertEquals(1, notAnsweredInquiryList.size());
        assertEquals(notAnsweredInquiry, notAnsweredInquiryList.get(0));
    }

    @Test
    void testSetAnswer() {
        long id = 1L;
        Inquiry testInquiry =
                new Inquiry(id, "title", InquiryCategory.COMPLAINT_SUBMISSION, "content", LocalDateTime.now());
        inquiryUserMap.put(id, testInquiry);

        Answer testAnswer = new Answer("content", LocalDateTime.now(), "csId");

        inquiryRepository.setAnswer(testAnswer, id);

        assertTrue(testInquiry.isAnswered());
        assertEquals(testAnswer, testInquiry.getAnswer());
    }
}