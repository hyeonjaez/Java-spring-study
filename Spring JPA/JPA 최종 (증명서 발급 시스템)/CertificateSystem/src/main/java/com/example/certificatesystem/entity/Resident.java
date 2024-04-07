package com.example.certificatesystem.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "resident")
@AllArgsConstructor
public class Resident {

    @Id
    @NotNull
    @Column(name = "resident_serial_number")
    private Integer residentSerialNumber;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "resident_registration_number")
    private String residentRegistrationNumber;

    @NotNull
    @Column(name = "gender_code")
    private String genderCode;

    @NotNull
    @Column(name = "birth_date")
    private String birthDate;

    @NotNull
    @Column(name = "birth_place_code")
    private String birthPlaceCode;

    @NotNull
    @Column(name = "registration_base_address")
    private String registrationBaseAddress;

    @Column(name = "death_date")
    private String deathDate;

    @Column(name = "death_place_code")
    private String deathPlaceCode;

    @Column(name = "death_place_address")
    private String deathPlaceAddress;
}
