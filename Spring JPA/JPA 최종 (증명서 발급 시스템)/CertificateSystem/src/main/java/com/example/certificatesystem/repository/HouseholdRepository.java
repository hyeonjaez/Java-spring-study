package com.example.certificatesystem.repository;


import com.example.certificatesystem.entity.Household;
import com.example.certificatesystem.repository.custom.HouseholdRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HouseholdRepository extends HouseholdRepositoryCustom, JpaRepository<Household, Integer> {
    Household findByHouseholdSerialNumber(Integer householdSerialNumber);

    Household deleteHouseholdByHouseholdSerialNumber(Integer householdSerialNumber);
}
