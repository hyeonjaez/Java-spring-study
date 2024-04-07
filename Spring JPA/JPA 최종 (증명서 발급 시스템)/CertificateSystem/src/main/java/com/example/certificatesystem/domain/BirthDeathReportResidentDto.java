package com.example.certificatesystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BirthDeathReportResidentDto {
    String name;
    String residentRegistrationNumber;
    String birthReportQualificationsCode;
    String emailAddress;
    String phoneNumber;
}
