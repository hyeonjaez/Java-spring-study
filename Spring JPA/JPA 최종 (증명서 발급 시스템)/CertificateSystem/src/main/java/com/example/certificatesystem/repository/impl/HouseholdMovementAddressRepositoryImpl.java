package com.example.certificatesystem.repository.impl;

import com.example.certificatesystem.domain.HouseholdMovementAddressDto;
import com.example.certificatesystem.entity.QHousehold;
import com.example.certificatesystem.entity.QHouseholdMovementAddress;
import com.example.certificatesystem.repository.custom.HouseholdMovementAddressRepositoryCustom;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


public class HouseholdMovementAddressRepositoryImpl extends QuerydslRepositorySupport implements
        HouseholdMovementAddressRepositoryCustom {


    public HouseholdMovementAddressRepositoryImpl() {
        super(HouseholdMovementAddressDto.class);
    }

    @Override
    public List<HouseholdMovementAddressDto> getHouseholdMovementAddresssDtoList(Integer householdSerialNumber) {
        QHousehold household = QHousehold.household;
        QHouseholdMovementAddress movementAddress = QHouseholdMovementAddress.householdMovementAddress;

        return from(household)
                .innerJoin(movementAddress).on(household.householdSerialNumber.eq(movementAddress.pk.householdSerialNumber))
                .where(household.householdSerialNumber.eq(householdSerialNumber))
                .select(Projections.constructor(HouseholdMovementAddressDto.class,
                        movementAddress.pk.houseMovementReportDate,
                        movementAddress.houseMovementAddress))

                .fetch();
    }
}