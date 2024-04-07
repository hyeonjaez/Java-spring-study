package com.nhnacademy.edu.springframework.project.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class ScoresTest {

    private Scores scores;

    @BeforeEach
    void setUp() {
        scores = CsvScores.getInstance();
        scores.load();
    }

    @Test
    void singleInstanceTest() {
        Assertions.assertEquals(scores.hashCode(), CsvScores.getInstance().hashCode());
    }


    @Test
    void load() {
        List<Score> scoreList = ((CsvScores) scores).getScoreCollection();
        assertNotNull(scoreList);
        assertEquals(3, scoreList.size());
    }

    @Test
    void findAll() {
        List<Score> scoreList = scores.findAll();
        assertNotNull(scoreList);

        assertEquals(3, scoreList.size());
        assertEquals(scoreList, ((CsvScores) scores).getScoreCollection());
        Score firstData = scoreList.get(0);

        assertEquals(1, firstData.getStudentSeq());
        assertEquals(30, firstData.getScore());
    }


}