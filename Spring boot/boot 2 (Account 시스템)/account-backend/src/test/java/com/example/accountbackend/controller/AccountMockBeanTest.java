package com.example.accountbackend.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.example.accountbackend.Repository.AccountRepository;
import com.example.accountbackend.domain.Account;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class AccountMockBeanTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountRepository accountRepository;

    @Test
    void testGetAccounts() throws Exception {
        given(accountRepository.findAll()).willReturn(List.of(new Account(122345L, "jaehyeon", "123-123", 10000)));

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user",
                        equalTo("hyeoni")));
    }

    @Test
    void testGetAccount(){
        Long id = 12345L;
        given(accountRepository.findById(id).orElse(null)).willReturn(new Account(123445L, "jaehyeon", "123-321", 10000));
//    mockMvc.perform(MockMvcRequestBuilders.get("'accounts"));

    }



}