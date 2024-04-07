package com.example.certificatesystem.domain;


import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class BirthDeathReportModifyRequest {
    @NotNull
    String birthDeathReportDate;

    String birthReportQualificationsCode;

    String deathReportQualificationsCode;

    String emailAddress;

    String phoneNumber;
}
