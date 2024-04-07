package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.Collection;
import java.util.stream.Collectors;

public class DefaultStudentService implements StudentService {
    @Override
    public Collection<Student> getPassedStudents() {
        Students studentRepository = CsvStudents.getInstance();

        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getScore() != null && !student.getScore().isFail())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        Students studentRepository = CsvStudents.getInstance();

        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getScore() != null)
                .sorted((student1, student2) -> student2.getScore().getScore() - student1.getScore().getScore())
                .collect(Collectors.toList());
    }

}
