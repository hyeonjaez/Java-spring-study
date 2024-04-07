package com.nhnacademy.exam.parser.impl;

import com.nhnacademy.exam.domain.EmployeeInfo;
import com.nhnacademy.exam.parser.DepartmentParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TextDepartmentParser implements DepartmentParser {

    @Override
    public String getFileType() {
        return "txt";
    }

    @Override
    public List<EmployeeInfo> parsing(File file) throws IOException {
        List<EmployeeInfo> list = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] linePart = line.split("\\|");
                if (linePart[1].contains("20")) {
                    list.add(new EmployeeInfo(linePart[1].trim(), linePart[2].trim(), linePart[3].trim(),
                            linePart[4].trim()));
                }
            }
        }

        return list;
    }


}
