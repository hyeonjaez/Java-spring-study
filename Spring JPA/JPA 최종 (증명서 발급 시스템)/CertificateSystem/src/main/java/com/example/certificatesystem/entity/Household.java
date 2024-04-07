package com.example.certificatesystem.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
@Table(name = "household")
@AllArgsConstructor
public class Household {

    @Id
    @NotNull
    @Column(name = "household_serial_number")
    private Integer householdSerialNumber;

    @NotNull
    @Column(name = "household_resident_serial_number")
    private Integer householdResidentSerialNumber;

    @NotNull
    @Column(name = "household_composition_date")
    private String householdCompositionDate;

    @NotNull
    @Column(name = "household_composition_reason_code")
    private String householdCompositionReasonCode;

    @NotNull
    @Column(name = "current_house_movement_address")
    private String currentHouseMovementAddress;

    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;
}
