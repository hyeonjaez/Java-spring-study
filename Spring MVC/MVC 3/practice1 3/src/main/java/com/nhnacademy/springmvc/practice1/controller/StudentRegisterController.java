package com.nhnacademy.springmvc.practice1.controller;


import com.nhnacademy.springmvc.practice1.domain.Student;
import com.nhnacademy.springmvc.practice1.domain.StudentRequest;
import com.nhnacademy.springmvc.practice1.repository.StudentRepository;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student/register")
public class StudentRegisterController {
    private final StudentRepository studentRepository;

    public StudentRegisterController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String studentRegisterForm() {

        return "studentRegister";
    }

    @PostMapping
    public ModelAndView registerStudent(@Valid @ModelAttribute StudentRequest studentRequest) {
        Student student = studentRepository.register(studentRequest.getPassword(), studentRequest.getName(),
                studentRequest.getEmail(),
                studentRequest.getScore(), studentRequest.getComment());

        ModelAndView modelAndView = new ModelAndView("studentView");
        modelAndView.addObject("student", student);

        return modelAndView;
    }

}