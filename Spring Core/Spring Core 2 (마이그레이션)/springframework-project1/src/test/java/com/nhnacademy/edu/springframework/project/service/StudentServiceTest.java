package com.nhnacademy.edu.springframework.project.service;

import java.util.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentServiceTest {

    @BeforeEach
    public void setUp() {
        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();
    }

    @Test
    void getPassedStudents() {
        DefaultStudentService defaultStudentService = new DefaultStudentService();
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
        DefaultStudentService defaultStudentService = new DefaultStudentService();
        Collection<Student> students = defaultStudentService.getStudentsOrderByScore();

        int prevScore = Integer.MAX_VALUE;
        for (Student student : students) {
            int currentScore = student.getScore().getScore();
            Assertions.assertTrue(currentScore <= prevScore);
            prevScore = currentScore;
        }
    }
}