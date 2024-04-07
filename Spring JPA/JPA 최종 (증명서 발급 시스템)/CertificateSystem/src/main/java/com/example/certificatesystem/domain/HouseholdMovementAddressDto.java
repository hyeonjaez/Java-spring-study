package com.example.certificatesystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class HouseholdMovementAddressDto {
    private String houseMovementReportDate;
    private String houseMovementAddress;
}