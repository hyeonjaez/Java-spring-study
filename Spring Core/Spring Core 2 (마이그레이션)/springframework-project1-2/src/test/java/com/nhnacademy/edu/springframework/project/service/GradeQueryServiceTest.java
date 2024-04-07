package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import com.nhnacademy.edu.springframework.project.repository.Score;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(JavaConfig.class)
class GradeQueryServiceTest {

    @Autowired
    DataLoadService csvDataLoadService;
    @Autowired
    GradeQueryService defaultGradeQueryService;

    @BeforeEach
    public void setUp() {
        csvDataLoadService.loadAndMerge();
    }

    @Test
    void getScoreByStudentName() {
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
        int seq = 1;
        Score score = defaultGradeQueryService.getScoreByStudentSeq(1);

        Assertions.assertEquals(1, score.getStudentSeq());
        Assertions.assertEquals(30, score.getScore());
    }
}