package com.example.certificatesystem.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class HouseholdDto {
    private String name;
    private Integer householdSerialNumber;
    private Integer householdResidentSerialNumber;
    private String householdCompositionDate;
    private String householdCompositionReasonCode;
    private String currentHouseMovementAddress;
}