package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(JavaConfig.class)
class ScoresTest {

    @Autowired
    private Scores csvScores;

    @BeforeEach
    public void setUp(){
        csvScores.load();
    }

    @Test
    void load() {
        List<Score> scoreList = ((CsvScores)csvScores).getScoreCollection();
        Assertions.assertNotNull(scoreList);
        assertEquals(3, scoreList.size());
    }

    @Test
    void findAll() {
        List<Score> scoreList = csvScores.findAll();
        Assertions.assertNotNull(scoreList);

        assertEquals(3, scoreList.size());
        assertEquals(scoreList, ((CsvScores) csvScores).getScoreCollection());
        Score firstData = scoreList.get(0);

        assertEquals(1, firstData.getStudentSeq());
        assertEquals(30, firstData.getScore());
    }
}