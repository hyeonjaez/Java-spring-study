package com.nhnacademy.exam.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.domain.dto.DepartmentDto;
import com.nhnacademy.exam.domain.request.DepartmentRegisterRequest;
import com.nhnacademy.exam.domain.response.DepartmentRegisterResponse;
import com.nhnacademy.exam.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Transactional
@AutoConfigureMockMvc
class DepartmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DepartmentService departmentService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void registerDepartmentTest() throws Exception {
        DepartmentRegisterRequest departmentRegisterRequest = new DepartmentRegisterRequest("L001", "jaehyeon");
        DepartmentRegisterResponse departmentRegisterResponse = new DepartmentRegisterResponse(
                departmentRegisterRequest.getId());

        when(departmentService.create(departmentRegisterRequest)).thenReturn(departmentRegisterResponse);

        mockMvc.perform(post("/departments").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentRegisterResponse)).header("X-USER-ID", "nhnacademy"))
                .andExpect(status().isCreated());
    }


    @Test
    void getDepartmentTest() throws Exception {
        String departmentId = "qwer";
        DepartmentDto departmentDto = new DepartmentDto() {
            @Override
            public String getId() {
                return departmentId;
            }

            @Override
            public String getName() {
                return "jaehyeon";
            }
        };

        when(departmentService.getDepartment(departmentId)).thenReturn(departmentDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/departments/{departmentId}", departmentId)
                        .header("X-USER-ID", "nhnacademy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("jaehyeon"))
                .andExpect(jsonPath("$.id").value("qwer"));
    }


}