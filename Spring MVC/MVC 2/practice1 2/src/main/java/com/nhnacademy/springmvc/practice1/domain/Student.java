package com.nhnacademy.springmvc.practice1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter

@AllArgsConstructor
public class Student {
    @Setter
    private long id;
    @Setter
    private String name;
    @Setter
    private String email;
    @Setter
    private int score;
    @Setter
    private String comment;
}