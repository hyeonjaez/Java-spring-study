package com.example.certificatesystem.repository.impl;

import com.example.certificatesystem.domain.HouseholdCompositionResidentDto;
import com.example.certificatesystem.entity.QHouseholdCompositionResident;
import com.example.certificatesystem.entity.QResident;
import com.example.certificatesystem.repository.custom.HouseholdCompositionResidentRepositoryCustom;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class HouseholdCompositionResidentRepositoryImpl extends QuerydslRepositorySupport implements
        HouseholdCompositionResidentRepositoryCustom {

    public HouseholdCompositionResidentRepositoryImpl() {
        super(HouseholdCompositionResidentDto.class);
    }

    @Override
    public List<HouseholdCompositionResidentDto> getHouseholdCompositionResidentList(Integer residentSerialNumber) {
        QResident resident = QResident.resident;
        QHouseholdCompositionResident compositionResident = QHouseholdCompositionResident.householdCompositionResident;

        return from(resident)
                .innerJoin(compositionResident)
                .on(resident.residentSerialNumber.eq(compositionResident.pk.residentSerialNumber))
                .where(compositionResident.pk.householdSerialNumber.eq(residentSerialNumber))
                .select(Projections.constructor(HouseholdCompositionResidentDto.class,
                        compositionResident.householdRelationshipCode,
                        resident.name,
                        resident.residentRegistrationNumber,
                        compositionResident.reportDate,
                        compositionResident.householdCompositionChangeReasonCode))
                .fetch();
    }

}