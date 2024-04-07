package com.example.student.controller;

import com.example.student.domain.StudentDto;
import com.example.student.domain.StudentModifyRequest;
import com.example.student.domain.StudentRegistryRequest;
import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public String mainPage() {
        return "loginSuccess";
    }

    @GetMapping("/student/registry")
    public String registryStudent() {
        return "registryForm";
    }


    @PostMapping("/student/registry")
    public String registryStudentForm(@Valid @ModelAttribute StudentRegistryRequest studentRegistryRequest) {
        Student student = new Student(
                studentRegistryRequest.getId(),
                studentRegistryRequest.getPassword(),
                studentRegistryRequest.getName(),
                studentRegistryRequest.getEmail(),
                studentRegistryRequest.getScore(),
                studentRegistryRequest.getComment());

        studentService.save(student);

        return "redirect:/student/view/" + student.getId();
    }

    @GetMapping("/student/view/{id}")
    public String viewStudent(@PathVariable("id") Long id, Model model) {
        StudentDto studentInfo = studentService.getStudentInfo(id);
        model.addAttribute("student", studentInfo);

        return "studentView";
    }

    @GetMapping("/student/modify/{id}")
    public String modifyStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudent(id);

        model.addAttribute("student", student);
        return "studentModify";
    }

    @PostMapping("/student/modify/{id}")
    public String modifyStudentForm(@Valid @ModelAttribute StudentModifyRequest studentModifyRequest,
                                    @PathVariable("id") Long id) {
        Student student = new Student(id, studentModifyRequest.getPassword(), studentModifyRequest.getName(),
                studentModifyRequest.getEmail(), studentModifyRequest.getScore(), studentModifyRequest.getComment());

        Student modifyStudent = studentService.update(student);

        return "redirect:/student/view/" + id;


    }


}
