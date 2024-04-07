package com.example.springmvcfinal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class User {
    String id;
    String password;
    String name;
    UserCategory userCategory;
}
