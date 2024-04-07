package com.example.certificatesystem.domain;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BirthDeathReportRequest {

    @NotNull
    Integer reportResidentSerialNumber;
    @NotNull
    String birthDeathReportDate;

    String birthReportQualificationsCode;

    String deathReportQualificationsCode;

    String emailAddress;

    String phoneNumber;
}
