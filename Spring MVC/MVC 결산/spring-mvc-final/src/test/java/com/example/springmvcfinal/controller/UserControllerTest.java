package com.example.springmvcfinal.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.springmvcfinal.domain.Inquiry;
import com.example.springmvcfinal.domain.InquiryCategory;
import com.example.springmvcfinal.repository.UserInquiryRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserInquiryRepository userInquiryRepository;

    @BeforeEach
    void setUp() {
        userInquiryRepository = mock(UserInquiryRepository.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userInquiryRepository)).build();
    }

    @Test
    void testCsMain() throws Exception {
        String userId = "userId";
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", userId);

        List<Inquiry> mockInquiryList = new ArrayList<>();
        mockInquiryList.add(new Inquiry(1L, "title1", null, "content1", null));
        mockInquiryList.add(new Inquiry(2L, "title2", null, "content2", null));

        when(userInquiryRepository.getInquiryList(userId)).thenReturn(mockInquiryList);

        mockMvc.perform(get("/cs").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("user_main"))
                .andExpect(model().attribute("inquiryList", mockInquiryList));

    }

    @Test
    void testCsMainWithCategory() throws Exception {
        String userId = "userId";
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", userId);
        String category = InquiryCategory.COMPLAINT_SUBMISSION.getValue();

        List<Inquiry> mockInquiryList = new ArrayList<>();
        mockInquiryList.add(new Inquiry(1L, "title1", InquiryCategory.COMPLAINT_SUBMISSION, "content1", null));
        mockInquiryList.add(new Inquiry(2L, "title2", InquiryCategory.COMPLAINT_SUBMISSION, "content2", null));

        when(userInquiryRepository.getInquiryListByCategory(userId, category)).thenReturn(mockInquiryList);

        mockMvc.perform(get("/cs")
                        .session(session)
                        .param("category", category))
                .andExpect(status().isOk())
                .andExpect(view().name("user_main"))
                .andExpect(model().attribute("inquiryList", mockInquiryList));

    }

}