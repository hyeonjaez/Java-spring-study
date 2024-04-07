package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.annotation.TimeChecker;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsvDataLoadService implements DataLoadService {

    private Scores csvScores;
    private Students csvStudents;

    @Autowired
    public CsvDataLoadService(Scores csvScores, Students csvStudents) {
        this.csvScores = csvScores;
        this.csvStudents = csvStudents;
    }

    @TimeChecker
    @Override
    public void loadAndMerge() {
        Scores scores = this.csvScores;
        scores.load();

        Students students = this.csvStudents;
        students.load();
        students.merge(scores.findAll());
    }
}
