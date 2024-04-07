package com.example.certificatesystem.domain;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HouseholdMovementAddressRequest {
    @NotNull
    Integer householdSerialNumber;

    @NotNull
    String houseMovementReportDate;

    @NotNull
    String houseMovementAddress;

    @NotNull
    String lastAddressYOrN;
}
