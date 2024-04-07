package com.nhnacademy.springmvc.practice1.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Data
@Setter
@Getter
@NoArgsConstructor
public class StudentRequest {

    @NotBlank
    @Size(min = 1)
    String password;
    @NotBlank
    @Size(min = 1)
    String name;

    @Email
    String email;

    @Min(0)
    @Max(100)
    int score;

    @NotBlank
    @Size(min = 1, max = 200)
    String comment;
}