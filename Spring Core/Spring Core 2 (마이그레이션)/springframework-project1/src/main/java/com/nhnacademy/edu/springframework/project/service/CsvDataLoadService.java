package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;

public class CsvDataLoadService implements DataLoadService {
    @Override
    public void loadAndMerge() {
        Scores scores = CsvScores.getInstance();
        scores.load();

        Students students = CsvStudents.getInstance();
        students.load();
        students.merge(scores.findAll());
    }
}
