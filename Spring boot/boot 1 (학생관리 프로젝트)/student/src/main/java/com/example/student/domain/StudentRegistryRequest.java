package com.example.student.domain;


import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegistryRequest {

    @NotNull
    @Min(1)
    private Long id;

    @NotNull
    @Size(min = 1)
    private String password;

    @NotNull
    @Size(min = 1)
    private String name;

    @Email
    private String email;

    @Min(0)
    @Max(100)
    private int score;

    @Size(min = 1, max = 200)
    private String comment;
}
