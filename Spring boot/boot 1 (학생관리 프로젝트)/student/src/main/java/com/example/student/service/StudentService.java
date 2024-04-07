package com.example.student.service;

import com.example.student.domain.StudentDto;
import com.example.student.entity.Student;
import org.springframework.transaction.annotation.Transactional;

public interface StudentService {
    @Transactional
    Student save(Student student);

    Student getStudent(Long id);

    boolean matches(Long id, String password);
    StudentDto getStudentInfo(Long id);
    @Transactional
    Student update(Student student);
}
