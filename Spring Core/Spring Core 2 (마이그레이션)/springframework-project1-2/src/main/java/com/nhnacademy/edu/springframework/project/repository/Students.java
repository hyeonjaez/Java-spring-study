package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;

import java.util.Collection;
import org.springframework.stereotype.Repository;


public interface Students {
    void load();

    Collection<Student> findAll();

    void merge(Collection<Score> scores);
}
