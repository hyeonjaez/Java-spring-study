package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataLoadServiceTest {

    @Test
    void loadAndMerge() {
        DataLoadService dataLoadService = new CsvDataLoadService();
        Students students = CsvStudents.getInstance();
        dataLoadService.loadAndMerge();

        Collection<Student> studentCollection = ((CsvStudents) students).getStudentCollection();
        Assertions.assertEquals(4, studentCollection.size());
        Student firstData = (Student) studentCollection.toArray()[0];
        Assertions.assertEquals(1, firstData.getSeq());
        Assertions.assertEquals("A", firstData.getName());
        Assertions.assertEquals(1, firstData.getScore().getStudentSeq());
        Assertions.assertEquals(30, firstData.getScore().getScore());
    }
}