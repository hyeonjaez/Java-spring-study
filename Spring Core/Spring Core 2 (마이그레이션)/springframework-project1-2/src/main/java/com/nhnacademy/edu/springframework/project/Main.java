package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.GradeQueryService;
import com.nhnacademy.edu.springframework.project.service.Student;
import java.util.Collection;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);
        DataLoadService dataLoadService = ac.getBean("csvDataLoadService", DataLoadService.class);

        dataLoadService.loadAndMerge();

        StudentService studentService = ac.getBean("defaultStudentService", StudentService.class);

        GradeQueryService gradeQueryService = ac.getBean("defaultGradeQueryService", GradeQueryService.class);

        List<Score> scoreByStudentName = gradeQueryService.getScoreByStudentName("A");

        Collection<Student> passedStudents = studentService.getPassedStudents();

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();

        Score scoreByStudentSeq = gradeQueryService.getScoreByStudentSeq(1);

        System.out.println(passedStudents);

        System.out.println(orderedStudents);

        scoreByStudentName.stream().forEach(System.out::println);

        System.out.println(scoreByStudentSeq);
    }
}