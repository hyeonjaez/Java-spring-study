package com.nhnacademy.exam.parser.impl;

import com.nhnacademy.exam.domain.EmployeeInfo;
import com.nhnacademy.exam.parser.DepartmentParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CsvDepartmentParser implements DepartmentParser {

    @Override
    public String getFileType() {
        return "csv";
    }

    @Override
    public List<EmployeeInfo> parsing(File file) throws IOException {
        String line;
        List<EmployeeInfo> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                List<String> lineContents =
                        Arrays.stream(line.replace(" ", "").split(",", -1)).collect(Collectors.toList());
                if (Objects.nonNull(lineContents) && lineContents.size() > 1) {
                    list.add(new EmployeeInfo(lineContents.get(0), lineContents.get(1), lineContents.get(2),
                            lineContents.get(3)));
                }
            }

        }


        return list;
    }


}
