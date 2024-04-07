package com.nhnacademy.exam.parser;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentParserResolver {
    private final List<DepartmentParser> departmentParserList;

    public DepartmentParser getDepartmentParser(String fileName) {
        int index = fileName.lastIndexOf(".");
        String extension = fileName.substring(index + 1);
        for (DepartmentParser departmentParser : departmentParserList) {
            if (departmentParser.matchFileType(extension)) {
                return departmentParser;
            }
        }
        return null;
    }

}
