package com.nhnacademy.edu.springframework.project.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Repository;

@Repository
public class CsvScores implements Scores {

    private List<Score> scoreCollection;

    @Override
    public void load() {
        scoreCollection = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getResourceAsStream("/data/score.csv"), "file not exist"),
                        StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Score score = new Score(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
                scoreCollection.add(score);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error parse file", e);
        }
    }

    @Override
    public List<Score> findAll() {
        return this.scoreCollection;
    }

    public List<Score> getScoreCollection() {
        return scoreCollection;
    }
}
