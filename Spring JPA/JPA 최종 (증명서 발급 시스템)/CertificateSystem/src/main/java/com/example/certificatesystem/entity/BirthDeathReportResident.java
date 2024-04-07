package com.example.certificatesystem.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "birth_death_report_resident")
public class BirthDeathReportResident {

    @EmbeddedId
    @NotNull
    Pk pk;

    @NotNull
    @Column(name = "report_resident_serial_number")
    Integer reportResidentSerialNumber;

    @NotNull
    @Column(name = "birth_death_report_date")
    String birthDeathReportDate;

    @Column(name = "birth_report_qualifications_code")
    String birthReportQualificationsCode;

    @Column(name = "death_report_qualifications_code")
    String deathReportQualificationsCode;

    @Column(name = "email_address")
    String emailAddress;

    @Column(name = "phone_number")
    String phoneNumber;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;


    @Embeddable
    @NoArgsConstructor
    @Setter
    @Getter
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class Pk implements Serializable {
        @NotNull
        @Column(name = "resident_serial_number")
        Integer residentSerialNumber;

        @NotNull
        @Column(name = "birth_death_type_code")
        String birthDeathTypeCode;
    }
}
