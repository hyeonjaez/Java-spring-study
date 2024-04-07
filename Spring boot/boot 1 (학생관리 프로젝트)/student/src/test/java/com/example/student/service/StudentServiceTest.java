package com.example.student.service;

import com.example.student.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @Test
    void saveTest() {
        Student student = new Student(999L, "qwer", "정재현", "123@naver.com", 90, "똑똑한 청년");
//        Student saveStudent = studentService.save(student);
//        Assertions.assertEquals(student.getId(), saveStudent.getId());
//        Assertions.assertEquals(student.getPassword(), saveStudent.getPassword());
//        Assertions.assertEquals(student.getName(), saveStudent.getName());
//        Assertions.assertEquals(student.getEmail(), saveStudent.getEmail());
    }

    @Test
    void saveExistTest() {
        Student student = new Student(999L, "qwer", "정재현", "123@naver.com", 90, "똑똑한 청년");
        Assertions.assertThrows(IllegalStateException.class, () -> studentService.save(student));
    }

    @Test
    void getStudentTest() {
        Student student = new Student(999L, "qwer", "정재현", "123@naver.com", 90, "똑똑한 청년");
        Student getStudent = studentService.getStudent(999L);
        Assertions.assertEquals(student.getId(), getStudent.getId());
    }

    @Test
    void matchesTest(){
        Assertions.assertTrue(studentService.matches(999L, "qwer"));
        Assertions.assertFalse(studentService.matches(999L, "qwe"));
    }

    @Test
    void updateTest(){
        Student student = new Student(43L, "qwer", "재현", "123@naver.com", 89, "똑똑한 청년");
        Student updateStudent = studentService.update(student);
        Assertions.assertEquals(student.getId(), updateStudent.getId());
    }

}