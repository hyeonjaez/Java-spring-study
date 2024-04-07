package com.nhnacademy.exam.parser.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.parser.DepartmentParser;
import com.nhnacademy.exam.parser.DepartmentParserResolver;
import com.nhnacademy.exam.parser.impl.CsvDepartmentParser;
import com.nhnacademy.exam.parser.impl.JsonDepartmentParser;
import com.nhnacademy.exam.parser.impl.TextDepartmentParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DepartmentParserServiceTest {

    static DepartmentParserResolver departmentParserResolver;
    static TextDepartmentParser textDepartmentParser = new TextDepartmentParser();
    static CsvDepartmentParser csvDepartmentParser = new CsvDepartmentParser();
    static JsonDepartmentParser jsonDepartmentParser = new JsonDepartmentParser(new ObjectMapper());

    @BeforeAll
    static void beforeAll(){
        departmentParserResolver = new DepartmentParserResolver(
                List.of(textDepartmentParser, jsonDepartmentParser, csvDepartmentParser));
    }

    @Order(1)
    @Test
    @DisplayName("DepartmentParserResolver : TextDepartmentParser")
    void getDepartmentParserFromText() {
        String fileName="department-1.txt";
        DepartmentParser departmentParser = departmentParserResolver.getDepartmentParser(fileName);
        Assertions.assertThat(departmentParser).isInstanceOf(TextDepartmentParser.class);
    }

    @Order(2)
    @Test
    @DisplayName("DepartmentParserResolver : JsonDepartmentParser")
    void getDepartmentParserFromJson() {
        String fileName="department.json";
        DepartmentParser departmentParser = departmentParserResolver.getDepartmentParser(fileName);
        Assertions.assertThat(departmentParser).isInstanceOf(JsonDepartmentParser.class);
    }
    @Order(3)
    @Test
    @DisplayName("DepartmentParserResolver : CsvDepartmentParser")
    void getDepartmentParserFromCsv() {
        String fileName="department.csv";
        DepartmentParser departmentParser = departmentParserResolver.getDepartmentParser(fileName);
        Assertions.assertThat(departmentParser).isInstanceOf(CsvDepartmentParser.class);
    }
    @Order(4)
    @Test
    @DisplayName("DepartmentParserResolver : XmlDepartmentParser, return:null")
    void getDepartmentParserFromXml() {
        String fileName="department.xml";
        DepartmentParser departmentParser = departmentParserResolver.getDepartmentParser(fileName);
        Assertions.assertThat(departmentParser).isNull();
    }

    @Order(5)
    @ParameterizedTest
    @MethodSource("fileListParam")
    @DisplayName("텍스트파일 파싱")
    void parsing(String filePath, int size) throws IOException {

        Resource resource = (Resource) new PathMatchingResourcePatternResolver().getResource("classpath:" + filePath);

        List<?> result = departmentParserResolver
                .getDepartmentParser(filePath)
                .parsing(resource.getFile());

        Assertions.assertThat(result.size()).isEqualTo(size);
    }


    private static Stream<Arguments> fileListParam(){
        return Stream.of(
                Arguments.of("data/department-1.txt",6),
                Arguments.of("data/department-2.txt",10),
                Arguments.of("data/department.csv",10),
                Arguments.of("data/department.json",10)
        );
    }

}
