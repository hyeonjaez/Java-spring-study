package com.nhnacademy.edu.springframework.project.service;


import com.nhnacademy.edu.springframework.project.repository.Score;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeQueryServiceTest {

    @BeforeEach
    public void setUp() {
        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();
    }

    @Test
    void getScoreByStudentName() {
        DefaultGradeQueryService defaultGradeQueryService = new DefaultGradeQueryService();
        String name = "A";

        List<Score> scoreList = defaultGradeQueryService.getScoreByStudentName(name);
        Score firstScore = scoreList.get(0);
        Score secondScore = scoreList.get(1);
        Assertions.assertEquals(1, firstScore.getStudentSeq());
        Assertions.assertEquals(30, firstScore.getScore());

        Assertions.assertEquals(3, secondScore.getStudentSeq());
        Assertions.assertEquals(70, secondScore.getScore());
    }

    @Test
    void getScoreByStudentSeq() {
        DefaultGradeQueryService defaultGradeQueryService = new DefaultGradeQueryService();
        int seq = 1;
        Score score = defaultGradeQueryService.getScoreByStudentSeq(1);

        Assertions.assertEquals(1, score.getStudentSeq());
        Assertions.assertEquals(30, score.getScore());
    }
}