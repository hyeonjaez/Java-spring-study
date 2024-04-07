package com.example.springmvcfinal.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.springmvcfinal.domain.Inquiry;
import com.example.springmvcfinal.domain.InquiryCategory;
import com.example.springmvcfinal.repository.InquiryRepository;
import com.example.springmvcfinal.repository.UserInquiryRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

class InquiryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private InquiryRepository mockInquiryRepository;

    @Mock
    private UserInquiryRepository mockUserInquiryRepository;

    @InjectMocks
    private InquiryController inquiryController;

    @BeforeEach
    void setUp() {
        mockInquiryRepository = mock(InquiryRepository.class);
        mockUserInquiryRepository = mock(UserInquiryRepository.class);
        inquiryController = new InquiryController(mockInquiryRepository, mockUserInquiryRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(inquiryController).build();
    }

    @Test
    void testInquiryDetail() throws Exception {
        long inquiryId = 1L;
        Inquiry inquiry = new Inquiry(inquiryId, null, null, null, null);
        when(mockInquiryRepository.getInquiryList(inquiryId)).thenReturn(inquiry);

        mockMvc.perform(get("/cs/inquiry-details")
                        .param("inquiryId", String.valueOf(inquiryId)))
                .andExpect(status().isOk())
                .andExpect(view().name("inquiry_detail"))
                .andExpect(model().attribute("inquiry", inquiry));
    }

    @Test
    void testInquiry() throws Exception {
        mockMvc.perform(get("/cs/inquiry"))
                .andExpect(status().isOk())
                .andExpect(view().name("inquiryForm"));
    }


    @Test
    void testDoInquiry() throws Exception {
        String userId = "testUser";
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", userId);

        String title = "Test Title";
        String category = InquiryCategory.REFUND_OR_EXCHANGE.getValue();
        String content = "Test Content";

        InquiryCategory inquiryCategory = InquiryCategory.REFUND_OR_EXCHANGE;
        Inquiry mockInquiry = new Inquiry(1L, title, inquiryCategory, content, LocalDateTime.now());

        List<MultipartFile> mockFiles = new ArrayList<>();

        when(mockInquiryRepository.register(title, inquiryCategory, content, LocalDateTime.now())).thenReturn(
                mockInquiry);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/cs/inquiry")
                .param("title", title)
                .param("category", category)
                .param("content", content)
                .session(session)
                .flashAttr("file", mockFiles));

    }


}