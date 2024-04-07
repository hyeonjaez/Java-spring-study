package com.example.account.domain;


import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @NotNull
    private Long id;
    @NotNull
    private String user;
    @NotNull
    private String number;
    @NotNull
    private Integer balance;
}
