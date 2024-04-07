package com.example.springmvcfinal.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.springmvcfinal.domain.User;
import com.example.springmvcfinal.domain.UserCategory;
import com.example.springmvcfinal.repository.UserRepository;
import javax.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


class LoginControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(userRepository)).build();
    }

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(get("/cs/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"));
    }

    @Test
    void testLoginSession() throws Exception {
        when(userRepository.matches(anyString(), anyString())).thenReturn(true);
        mockMvc.perform(get("/cs/login").cookie(new Cookie("SESSION", "session")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs?id=session"));
    }

    @Test
    void testDoLogin() throws Exception {
        when(userRepository.matches("id", "password")).thenReturn(true);
        when(userRepository.getUser("id")).thenReturn(new User("id", "password", "jaehyeon", UserCategory.USER));

        mockMvc.perform(post("/cs/login")
                        .param("id", "id")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs?id=id"));
    }

    @Test
    void testAdminDoLogin() throws Exception {
        when(userRepository.matches("id", "password")).thenReturn(true);
        when(userRepository.getUser("id")).thenReturn(new User("id", "password", "jaehyeon", UserCategory.ADMIN));

        mockMvc.perform(post("/cs/login")
                        .param("id", "id")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/admin"));
    }

    @Test
    void testLogout() throws Exception {
        mockMvc.perform(get("/cs/logout").session(new MockHttpSession()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/login"));
    }

}