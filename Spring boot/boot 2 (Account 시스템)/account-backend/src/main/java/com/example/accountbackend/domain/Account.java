package com.example.accountbackend.domain;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Account {
    @Id
    @NotNull
    private Long id;
    @NotNull
    private String user;
    @NotNull
    private String number;
    @NotNull
    private Integer balance;
}