package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.annotation.TimeChecker;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultStudentService implements StudentService {

    private Students csvStudents;

    @Autowired
    public DefaultStudentService(Students csvStudents) {
        this.csvStudents = csvStudents;
    }

    @TimeChecker
    @Override
    public Collection<Student> getPassedStudents() {
        Students studentRepository = this.csvStudents;

        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getScore() != null && !student.getScore().isFail())
                .collect(Collectors.toList());
    }

    @TimeChecker
    @Override
    public Collection<Student> getStudentsOrderByScore() {
        Students studentRepository = this.csvStudents;

        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getScore() != null)
                .sorted((student1, student2) -> student2.getScore().getScore() - student1.getScore().getScore())
                .collect(Collectors.toList());
    }

}
