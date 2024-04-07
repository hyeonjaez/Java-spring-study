package com.example.student.service;

import com.example.student.domain.StudentDto;
import com.example.student.entity.Student;
import com.example.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        boolean exist = studentRepository.findById(student.getId()).isPresent();
        if (exist) {
            throw new IllegalStateException("exist :" + student);
        }
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public boolean matches(Long id, String password) {
        return studentRepository.findStudentByIdAndAndPassword(id, password).isPresent();
    }

    @Override
    public StudentDto getStudentInfo(Long id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }
}
