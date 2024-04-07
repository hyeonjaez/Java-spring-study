package com.example.springmvcfinal.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.springmvcfinal.domain.Answer;
import com.example.springmvcfinal.domain.Inquiry;
import com.example.springmvcfinal.repository.InquiryRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AdminControllerTest {
    private MockMvc mockMvc;

    @Mock
    private InquiryRepository inquiryRepository;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        inquiryRepository = mock(InquiryRepository.class);
        adminController = new AdminController(inquiryRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }


    @Test
    void testCsAdminMain() throws Exception {
        List<Inquiry> inquiryList = new ArrayList<>();
        Inquiry inquiry = new Inquiry(1L, "title", null, null, null);
        Inquiry inquiry2 = new Inquiry(2L, "Title 2", null, "Content 2", null);
        inquiryList.add(inquiry);
        inquiryList.add(inquiry2);

        when(inquiryRepository.notAnsweredInquiryList()).thenReturn(inquiryList);

        mockMvc.perform(get("/cs/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin_main"))
                .andExpect(model().attribute("notAnsweredInquiryList", inquiryList));
    }

    @Test
    void testAnswerAdmin() throws Exception {
        long inquiryId = 1L;
        Inquiry inquiry = new Inquiry(1L, "title", null, null, null);
        when(inquiryRepository.getInquiryList(inquiryId)).thenReturn(inquiry);
        mockMvc.perform(get("/cs/admin/answer")
                        .param("inquiry", String.valueOf(inquiryId)))
                .andExpect(status().isOk())
                .andExpect(view().name("answerForm"))
                .andExpect(model().attribute("inquiry", inquiry));
    }

    @Test
    void testAnswerForm() throws Exception {
        long inquiryId = 1L;
        String content = "content";
        String userId = "userId";

        Answer answer = new Answer(content, null, userId);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", userId);

        mockMvc.perform(post("/cs/admin/answer")
                        .param("inquiryId", String.valueOf(inquiryId))
                        .param("content", content)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/admin"));
    }


}