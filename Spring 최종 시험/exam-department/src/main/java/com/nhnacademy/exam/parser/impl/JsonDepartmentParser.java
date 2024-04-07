package com.nhnacademy.exam.parser.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.domain.EmployeeInfo;
import com.nhnacademy.exam.parser.DepartmentParser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class JsonDepartmentParser implements DepartmentParser {

    private final ObjectMapper objectMapper;

    @Override
    public String getFileType() {
        return "json";
    }

    @Override
    public List<EmployeeInfo> parsing(File file) throws IOException {
        String json = new String(Files.readAllBytes(file.toPath()));
        List<EmployeeInfo> result = objectMapper.readValue(json,
                objectMapper.getTypeFactory().constructCollectionType(List.class, EmployeeInfo.class));

        return result;
    }
}
