package com.example.certificatesystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BirthResidentDto {
    String birthDeathReportDate;
    String name;
    String genderCode;
    String birthDate;
    String birthPlaceCode;
    String registrationBaseAddress;
}
