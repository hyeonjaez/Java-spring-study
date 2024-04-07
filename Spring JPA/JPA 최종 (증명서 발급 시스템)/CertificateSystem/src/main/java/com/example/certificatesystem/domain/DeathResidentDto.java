package com.example.certificatesystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class DeathResidentDto {
    String birthDeathReportDate;
    String name;
    String residentRegistrationNumber;
    String deathDate;
    String deathPlaceCode;
    String deathPlaceAddress;
}
