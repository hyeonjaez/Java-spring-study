package com.example.certificatesystem.service;

import com.example.certificatesystem.domain.HouseholdCompositionResidentDto;
import com.example.certificatesystem.domain.HouseholdDto;
import com.example.certificatesystem.domain.HouseholdMovementAddressDto;
import com.example.certificatesystem.entity.Household;
import com.example.certificatesystem.entity.HouseholdMovementAddress;
import java.util.List;

public interface HouseholdService {
    Household saveHousehold(Household household);

    Household findHousehold(Integer householdSerialNumber);

    void deleteHousehold(Integer householdSerialNumber);

    HouseholdMovementAddress saveHouseholdMovementAddress(HouseholdMovementAddress householdMovementAddress);

    HouseholdMovementAddress findHouseholdMovementAddress(String houseMovementReportDate,
                                                          Integer householdSerialNumber);

    void deleteHouseholdMovementAddress(String houseMovementReportDate, Integer householdSerialNumber);

    HouseholdDto getHouseholdDtoByResidentSerialNumber(Integer residentSerialNumber);

    List<HouseholdCompositionResidentDto> getHouseholdCompositionResidentList(Integer residentSerialNumber);

    List<HouseholdMovementAddressDto> getHouseholdMovementAddresssDtoList(Integer householdSerialNumber);
}
