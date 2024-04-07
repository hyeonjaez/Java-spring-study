package com.nhnacademy.edu.springframework.project.repository;

import java.util.List;

public interface Scores {
    void load();
    List<Score> findAll();
}