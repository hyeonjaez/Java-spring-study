package com.nhnacademy.springmvc.practice1.repository;


import com.nhnacademy.springmvc.practice1.domain.Student;

public interface StudentRepository {
    boolean exists(long id);

    boolean matches(long id, String password);

    Student register(String password, String name, String email, int score, String comment);

    Student getStudent(long id);

    Student modify(Student student);

}
