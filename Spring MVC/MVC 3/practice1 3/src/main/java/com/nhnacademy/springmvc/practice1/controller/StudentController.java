package com.nhnacademy.springmvc.practice1.controller;


import com.nhnacademy.springmvc.practice1.domain.Student;
import com.nhnacademy.springmvc.practice1.domain.StudentRequest;
import com.nhnacademy.springmvc.practice1.exception.ResourceNotFoundException;
import com.nhnacademy.springmvc.practice1.repository.StudentRepository;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{studentId}")
    public String viewStudent(@PathVariable("studentId") long studentId, Model model) {
        Student student = studentRepository.getStudent(studentId);
        if (Objects.isNull(student)) {
            throw new ResourceNotFoundException(this.getClass().getName() + " is exception");
        }
        model.addAttribute("student", student);
        return "studentView";
    }

    @GetMapping(path = "/{studentId}", params = "hideScore")
    public String viewHideStudent(@RequestParam(value = "hideScore", defaultValue = "no") String hideScore,
                                  @PathVariable("studentId") long studentId, Model model) {

        Student student = studentRepository.getStudent(studentId);
        if ("yes".equals(hideScore)) {
            model.addAttribute("hideScore", true);
        }
        model.addAttribute("student", student);
        return "studentView";
    }

    @GetMapping("/{studentId}/modify")
    public String studentModifyForm(@PathVariable("studentId") long studentId, Model model) {
        Student student = studentRepository.getStudent(studentId);
        if (Objects.isNull(student)) {
            throw new ResourceNotFoundException(this.getClass().getName() + " is exception");
        }
        model.addAttribute("student", student);

        return "studentModify";
    }

    @PostMapping("/{studentId}/modify")
    public String modifyStudent(@Valid @ModelAttribute StudentRequest studentRequest,
                                @PathVariable("studentId") long studentId,
                                ModelMap modelMap) {
        // 학생 정보 수정
        Student existingStudent = studentRepository.getStudent(studentId);
        if (existingStudent != null) {
            existingStudent.setPassword(studentRequest.getPassword());
            existingStudent.setName(studentRequest.getName());
            existingStudent.setEmail(studentRequest.getEmail());
            existingStudent.setScore(studentRequest.getScore());
            existingStudent.setComment(studentRequest.getComment());
        }
        modelMap.addAttribute("student", existingStudent);

        return "studentView";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exceptionNotFound(ResourceNotFoundException exception, Model model) {
        model.addAttribute("exception", exception.getMessage());
        return "error";
    }
}