package com.nhnacademy.springmvc.practice1.repository;


import com.nhnacademy.springmvc.practice1.domain.Student;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final Map<Long, Student> studentMap = new HashMap<>();


    @Override
    public boolean exists(long id) {
        return studentMap.containsKey(id);
    }

    @Override
    public boolean matches(long id, String password) {
        return Optional.ofNullable(getStudent(id))
                .map(student -> student.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public Student register(String password, String name, String email, int score, String comment) {
        long id = maxId() + 1;
        Student student = new Student(id, password, name, email, score, comment);
        studentMap.put(id, student);
        return student;
    }

    @Override
    public Student getStudent(long id) {
        return exists(id) ? studentMap.get(id) : null;
    }

    @Override
    public Student modify(Student modifyStudent) {
        long id = modifyStudent.getId();
        if (exists(id)) {
            Student existingStudent = getStudent(id);

            existingStudent.setPassword(modifyStudent.getPassword());
            existingStudent.setName(modifyStudent.getName());
            existingStudent.setEmail(modifyStudent.getEmail());
            existingStudent.setScore(modifyStudent.getScore());
            existingStudent.setComment(modifyStudent.getComment());
            return existingStudent;
        } else {
            return null;
        }
    }

    private long maxId() {
        return studentMap.keySet().stream()
                .max(Comparator.comparingInt(Long::intValue))
                .orElse(0L);
    }
}
