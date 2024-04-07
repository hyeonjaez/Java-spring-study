package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultGradeQueryService implements GradeQueryService {

    @Override
    public List<Score> getScoreByStudentName(String name) {
        Students studentRepository = CsvStudents.getInstance();
        return studentRepository.findAll()
                .stream()
                .filter(student -> Objects.equals(student.getName(), name))
                .map(Student::getScore)
                .collect(Collectors.toList());
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        Students studentRepository = CsvStudents.getInstance();
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getSeq() == seq)
                .findFirst()
                .map(Student::getScore)
                .orElse(null);
    }
}
