package com.nhnacademy.springmvc.practice1.repository;


import com.nhnacademy.springmvc.practice1.domain.Student;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class StudentRepositoryImpl implements StudentRepository {
    private final Map<Long, Student> studentMap = new HashMap<>();


    @Override
    public boolean exists(long id) {
        return studentMap.containsKey(id);
    }

    @Override
    public Student register(String name, String email, int score, String comment) {
        long id = maxId() + 1;
        Student student = new Student(id, name, email, score, comment);
        studentMap.put(id, student);
        return student;
    }

    @Override
    public Student getStudent(long id) {
        return exists(id) ? studentMap.get(id) : null;
    }

    private long maxId() {
        return studentMap.keySet().stream()
                .max(Comparator.comparingInt(Long::intValue))
                .orElse(0L);
    }
}
