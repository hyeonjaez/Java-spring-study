package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentsTest {
    private Students students;

    @BeforeEach
    void beforeSet() {
        students = CsvStudents.getInstance();
        students.load();
    }

    @Test
    void singleInstanceTest() {
        Assertions.assertEquals(students.hashCode(), CsvStudents.getInstance().hashCode());
    }

    @Test
    void load() {
        CsvStudents csvStudents = (CsvStudents) students;
        Collection<Student> studentList = csvStudents.getStudentCollection();
        Assertions.assertNotNull(studentList);
        Assertions.assertEquals(4, studentList.size());
    }


    @Test
    void findAll() {
        Collection<Student> studentCollection = students.findAll();
        Assertions.assertNotNull(studentCollection);

        Assertions.assertEquals(4, studentCollection.size());
        Assertions.assertEquals(studentCollection, ((CsvStudents) students).getStudentCollection());
        Student firstData = (Student) studentCollection.toArray()[0];

        Assertions.assertEquals(1, firstData.getSeq());
        Assertions.assertEquals("A", firstData.getName());
    }


    @Test
    void merge() {
        List<Score> scoreList = List.of(
                new Score(1, 90),
                new Score(2, 80));
        students.merge(scoreList);

        Collection<Student> studentCollection = ((CsvStudents) students).getStudentCollection();
        Assertions.assertEquals(4, studentCollection.size());
        Student firstData = (Student) studentCollection.toArray()[0];

        Assertions.assertEquals(1, firstData.getSeq());
        Assertions.assertEquals("A", firstData.getName());
        Assertions.assertEquals(1, firstData.getScore().getStudentSeq());
        Assertions.assertEquals(90, firstData.getScore().getScore());
    }
}