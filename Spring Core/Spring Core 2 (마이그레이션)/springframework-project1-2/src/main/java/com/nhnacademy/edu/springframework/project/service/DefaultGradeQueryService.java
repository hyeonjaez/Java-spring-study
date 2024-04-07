package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.annotation.TimeChecker;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultGradeQueryService implements GradeQueryService {

    private Students csvStudents;

    @Autowired
    public DefaultGradeQueryService(Students csvStudents) {
        this.csvStudents = csvStudents;
    }

    @TimeChecker
    @Override
    public List<Score> getScoreByStudentName(String name) {
        Students studentRepository = this.csvStudents;

        return studentRepository.findAll()
                .stream()
                .filter(student -> Objects.equals(student.getName(), name))
                .map(Student::getScore)
                .collect(Collectors.toList());
    }

    @TimeChecker
    @Override
    public Score getScoreByStudentSeq(int seq) {
        Students studentRepository = this.csvStudents;

        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getSeq() == seq)
                .findFirst()
                .map(Student::getScore)
                .orElse(null);
    }
}
