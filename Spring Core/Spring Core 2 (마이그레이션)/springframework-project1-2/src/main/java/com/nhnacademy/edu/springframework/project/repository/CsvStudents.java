package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import org.springframework.stereotype.Repository;

@Repository
public class CsvStudents implements Students {
    private Collection<Student> studentCollection;

    @Override
    public void load() {
        studentCollection = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getResourceAsStream("/data/student.csv"), "file not exist"),
                        StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Student student = new Student(Integer.parseInt(data[0]), data[1]);
                studentCollection.add(student);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error parse file", e);
        }
    }

    @Override
    public Collection<Student> findAll() {
        return this.studentCollection;
    }


    @Override
    public void merge(Collection<Score> scores) {
        scores.forEach(score ->
                studentCollection.stream()
                        .filter(student -> score.getStudentSeq() == student.getSeq())
                        .findFirst()
                        .ifPresent(student -> student.setScore(score))
        );
    }

    public Collection<Student> getStudentCollection() {
        return studentCollection;
    }
}
