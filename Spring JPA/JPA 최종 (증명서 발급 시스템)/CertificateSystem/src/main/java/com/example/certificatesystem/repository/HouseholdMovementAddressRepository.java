package com.example.certificatesystem.repository;

import com.example.certificatesystem.entity.HouseholdMovementAddress;
import com.example.certificatesystem.repository.custom.HouseholdCompositionResidentRepositoryCustom;
import com.example.certificatesystem.repository.custom.HouseholdMovementAddressRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HouseholdMovementAddressRepository
        extends HouseholdMovementAddressRepositoryCustom, JpaRepository<HouseholdMovementAddress, Integer> {
    HouseholdMovementAddress findByPk_HouseMovementReportDateAndPk_HouseholdSerialNumber(String houseMovementReportDate,
                                                                                         Integer householdSerialNumber);

    void deleteHouseholdMovementAddressByPk_HouseMovementReportDateAndPk_HouseholdSerialNumber(
            String houseMovementReportDate, Integer householdSerialNumber);
}
