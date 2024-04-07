package com.nhnacademy.springmvc.practice1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    @Setter
    private Long id;
    @Setter
    private String password;
    @Setter
    private String name;
    @Setter
    private String email;
    @Setter
    private int score;
    @Setter
    private String comment;
}