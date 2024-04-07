package com.example.student.controller;

import com.example.student.domain.StudentDto;
import com.example.student.domain.StudentModifyRequest;
import com.example.student.domain.StudentRegistryRequest;
import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentRestController {
    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<Student> registryStudents(@Valid @RequestBody StudentRegistryRequest studentRegistryRequest) {
        Student student = new Student(studentRegistryRequest.getId(), studentRegistryRequest.getPassword(),
                studentRegistryRequest.getName(), studentRegistryRequest.getEmail(), studentRegistryRequest.getScore(),
                studentRegistryRequest.getComment());

        Student registryStudent = studentService.save(student);
        return ResponseEntity.ok().body(registryStudent);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<StudentDto> viewStudents(@PathVariable("studentId") Long studentId) {
        StudentDto studentDto = studentService.getStudentInfo(studentId);
        return ResponseEntity.ok().body(studentDto);
    }


    @PutMapping("/students/{studentId}")
    public ResponseEntity<Student> modifyStudents(@PathVariable("studentId") long studentId,
                                                  @Valid @RequestBody StudentModifyRequest studentModifyRequest) {
        Student modifyStudent =
                new Student(studentId, studentModifyRequest.getPassword(), studentModifyRequest.getName(),
                        studentModifyRequest.getEmail(), studentModifyRequest.getScore(),
                        studentModifyRequest.getComment());
        Student resultStudent = studentService.update(modifyStudent);
        return ResponseEntity.ok().body(resultStudent);
    }
}
