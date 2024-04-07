package com.example.certificatesystem.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class HouseholdCompositionResidentDto {
    private String householdRelationshipCode;
    private String name;
    private String residentRegistrationNumber;
    private LocalDate reportDate;
    private String reportDateHouseholdCompositionChangeReasonCode;
}