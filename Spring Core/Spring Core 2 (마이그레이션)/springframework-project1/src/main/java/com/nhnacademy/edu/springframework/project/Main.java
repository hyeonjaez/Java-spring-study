package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.DefaultGradeQueryService;
import com.nhnacademy.edu.springframework.project.service.DefaultStudentService;
import com.nhnacademy.edu.springframework.project.service.GradeQueryService;
import com.nhnacademy.edu.springframework.project.service.Student;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();

        DefaultStudentService studentService = new DefaultStudentService();
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);

        GradeQueryService gradeQueryService = new DefaultGradeQueryService();
        List<Score> scoreByStudentName = gradeQueryService.getScoreByStudentName("A");
        scoreByStudentName.stream().forEach(System.out::println);

        Score scoreByStudentSeq = gradeQueryService.getScoreByStudentSeq(1);
        System.out.println(scoreByStudentSeq);
    }
}
