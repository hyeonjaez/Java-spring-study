package com.example.student.repository;

import com.example.student.domain.StudentDto;
import com.example.student.entity.Student;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByIdAndAndPassword(Long id, String password);

    StudentDto getStudentById(Long id);

}
