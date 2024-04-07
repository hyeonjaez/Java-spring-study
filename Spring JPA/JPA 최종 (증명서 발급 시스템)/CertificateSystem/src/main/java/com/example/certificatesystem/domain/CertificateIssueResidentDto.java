package com.example.certificatesystem.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CertificateIssueResidentDto {
    private Long certificateConfirmationNumber;
    private LocalDate certificateIssueDate;
}