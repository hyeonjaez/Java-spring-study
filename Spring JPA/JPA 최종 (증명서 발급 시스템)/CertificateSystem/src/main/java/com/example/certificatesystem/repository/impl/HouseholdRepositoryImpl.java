package com.example.certificatesystem.repository.impl;

import com.example.certificatesystem.domain.HouseholdDto;
import com.example.certificatesystem.entity.QHousehold;
import com.example.certificatesystem.entity.QHouseholdCompositionResident;
import com.example.certificatesystem.entity.QResident;
import com.example.certificatesystem.repository.custom.HouseholdRepositoryCustom;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


public class HouseholdRepositoryImpl extends QuerydslRepositorySupport implements HouseholdRepositoryCustom {

    public HouseholdRepositoryImpl() {
        super(HouseholdDto.class);
    }

    @Override
    public HouseholdDto getHouseholdDtoByResidentSerialNumber(Integer residentSerialNumber) {
        QResident resident = QResident.resident;
        QHouseholdCompositionResident householdCompositionResident =
                QHouseholdCompositionResident.householdCompositionResident;
        QHousehold household = QHousehold.household;

        return from(resident)
                .innerJoin(householdCompositionResident)
                .on(resident.residentSerialNumber.eq(householdCompositionResident.pk.residentSerialNumber))
                .innerJoin(household)
                .on(householdCompositionResident.pk.householdSerialNumber.eq(household.householdSerialNumber))
                .where(resident.residentSerialNumber.eq(residentSerialNumber))
                .select(Projections.constructor(HouseholdDto.class,
                        resident.name,
                        household.householdSerialNumber,
                        household.householdResidentSerialNumber,
                        household.householdCompositionDate,
                        household.householdCompositionReasonCode,
                        household.currentHouseMovementAddress))
                .fetchOne();
    }
}
