package com.nhnacademy.springmvc.practice1.controller;

import com.nhnacademy.springmvc.practice1.domain.Student;
import com.nhnacademy.springmvc.practice1.domain.StudentRequest;
import com.nhnacademy.springmvc.practice1.exception.ResourceNotFoundException;
import com.nhnacademy.springmvc.practice1.repository.StudentRepository;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentRestController {
    private final StudentRepository studentRepository;

    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @GetMapping("/{studentId}")
    public ResponseEntity<Student> viewStudent(@PathVariable("studentId") Long studentId) {
        Student student = studentRepository.getStudent(studentId);

        if (Objects.isNull(student)) {
            throw new ResourceNotFoundException(this.getClass().getName() + " is exception");
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).eTag("1234").body(student);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> modifyStudent(@PathVariable("studentId") long studentId,
                                                 @Valid @RequestBody StudentRequest studentRequest) {
        Student existingStudent = studentRepository.getStudent(studentId);
        if (existingStudent == null) {
            throw new ResourceNotFoundException("Student not found with id: " + studentId);
        }
        Student modifyStudent = new Student(studentId, studentRequest.getPassword(), studentRequest.getName(),
                studentRequest.getEmail(), studentRequest.getScore(), studentRequest.getComment());
        studentRepository.modify(modifyStudent);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).eTag("").body(existingStudent);
    }

    @PostMapping
    public ResponseEntity<Student> registerStudent(@Valid @RequestBody StudentRequest studentRequest) {
        Student student = studentRepository.register(studentRequest.getPassword(),
                studentRequest.getName(), studentRequest.getEmail(),
                studentRequest.getScore(), studentRequest.getComment());

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).eTag("").body(student);
    }
}