package com.example.certificatesystem.domain;


import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class HouseholdRequest {

    @NotNull
    private Integer householdSerialNumber;

    @NotNull
    private Integer householdResidentSerialNumber;

    @NotNull
    private String householdCompositionDate;

    @NotNull
    private String householdCompositionReasonCode;

    @NotNull
    private String currentHouseMovementAddress;
}
