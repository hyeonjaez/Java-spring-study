package com.example.certificatesystem.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "certificate_issue")
public class CertificateIssue {
    @Id
    @NotNull
    @Column(name = "certificate_confirmation_number")
    Long certificateConfirmationNumber;

    @NotNull
    @Column(name = "resident_serial_number")
    Integer residentSerialNumber;

    @NotNull
    @Column(name = "certificate_type_code")
    String certificateTypeCode;

    @NotNull
    @Column(name = "certificate_issue_date")
    LocalDate certificateIssueDate;

}
