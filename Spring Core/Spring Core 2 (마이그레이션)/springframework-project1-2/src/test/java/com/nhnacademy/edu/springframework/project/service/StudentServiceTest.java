package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import java.util.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(JavaConfig.class)
class StudentServiceTest {

    @Autowired
    DataLoadService csvDataLoadService;
    @Autowired
    StudentService defaultStudentService;

    @BeforeEach
    public void setUp() {
        csvDataLoadService.loadAndMerge();
    }

    @Test
    void getPassedStudents() {
        Collection<Student> students = defaultStudentService.getPassedStudents();

        Student student = students.stream().findAny().orElse(null);
        Assertions.assertNotNull(student);
        Assertions.assertEquals(2, student.getSeq());
        Assertions.assertEquals("B", student.getName());
        Assertions.assertEquals(2, student.getScore().getStudentSeq());
        Assertions.assertEquals(80, student.getScore().getScore());
    }

    @Test
    void getStudentsOrderByScore() {
        Collection<Student> students = defaultStudentService.getStudentsOrderByScore();

        int prevScore = Integer.MAX_VALUE;
        for (Student student : students) {
            int currentScore = student.getScore().getScore();
            Assertions.assertTrue(currentScore <= prevScore);
            prevScore = currentScore;
        }
    }
}