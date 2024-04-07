package com.example.certificatesystem.repository.impl;

import com.example.certificatesystem.domain.BirthDeathReportResidentDto;
import com.example.certificatesystem.domain.BirthResidentDto;
import com.example.certificatesystem.domain.BirthResidentParentsDto;
import com.example.certificatesystem.domain.DeathResidentDto;
import com.example.certificatesystem.entity.BirthDeathReportResident;
import com.example.certificatesystem.entity.QBirthDeathReportResident;
import com.example.certificatesystem.entity.QFamilyRelationship;
import com.example.certificatesystem.entity.QResident;
import com.example.certificatesystem.repository.custom.BirthDeathReportResidentRepositoryCustom;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


public class BirthDeathReportResidentRepositoryImpl extends QuerydslRepositorySupport implements
        BirthDeathReportResidentRepositoryCustom {

    public BirthDeathReportResidentRepositoryImpl() {
        super(BirthDeathReportResident.class);
    }

    @Override
    public BirthResidentDto findResidentDto(String birthDeathTypeCode, Integer residentSerialNumber) {
        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;
        QResident resident = QResident.resident;
        return from(birthDeathReportResident)
                .innerJoin(resident)
                .on(birthDeathReportResident.pk.residentSerialNumber.eq(resident.residentSerialNumber))
                .where(birthDeathReportResident.pk.birthDeathTypeCode.eq(birthDeathTypeCode)
                        .and(birthDeathReportResident.resident.residentSerialNumber.eq(residentSerialNumber)))
                .select(
                        Projections.constructor(
                                BirthResidentDto.class,
                                birthDeathReportResident.birthDeathReportDate,
                                resident.name,
                                resident.genderCode,
                                resident.birthDate,
                                resident.birthPlaceCode,
                                resident.registrationBaseAddress
                        )
                ).fetchOne();
    }

    @Override
    public List<BirthResidentParentsDto> findResidentParent(Integer residentSerialNumber) {
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;
        QResident resident = QResident.resident;

        return from(familyRelationship)
                .innerJoin(resident)
                .on(resident.residentSerialNumber.eq(familyRelationship.pk.familyResidentSerialNumber))
                .where(familyRelationship.pk.baseResidentSerialNumber.eq(residentSerialNumber)
                        .and(familyRelationship.familyRelationshipCode.eq("부")
                                .or(familyRelationship.familyRelationshipCode.eq("모"))))
                .select(
                        Projections.constructor(
                                BirthResidentParentsDto.class,
                                resident.name,
                                resident.residentRegistrationNumber
                        )
                ).fetch();
    }

    @Override
    public BirthDeathReportResidentDto birthReportResident(Integer residentSerialNumber) {
        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;
        QResident resident = QResident.resident;

        return from(birthDeathReportResident)
                .innerJoin(resident)
                .on(resident.residentSerialNumber.eq(birthDeathReportResident.reportResidentSerialNumber))
                .where(birthDeathReportResident.pk.residentSerialNumber.eq(residentSerialNumber))
                .select(
                        Projections.constructor(
                                BirthDeathReportResidentDto.class,
                                resident.name,
                                resident.residentRegistrationNumber,
                                birthDeathReportResident.birthReportQualificationsCode,
                                birthDeathReportResident.emailAddress,
                                birthDeathReportResident.phoneNumber
                        )
                ).fetchOne();

    }

    @Override
    public DeathResidentDto findDeathResidentDto(String birthDeathTypeCode, Integer residentSerialNumber) {
        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;
        QResident resident = QResident.resident;

        return from(birthDeathReportResident)
                .innerJoin(resident)
                .on(resident.residentSerialNumber.eq(birthDeathReportResident.pk.residentSerialNumber))
                .where(birthDeathReportResident.pk.birthDeathTypeCode.eq(birthDeathTypeCode)
                        .and(birthDeathReportResident.pk.residentSerialNumber.eq(residentSerialNumber)))
                .select(
                        Projections.constructor(
                                DeathResidentDto.class,
                                birthDeathReportResident.birthDeathReportDate,
                                resident.name,
                                resident.residentRegistrationNumber,
                                resident.deathDate,
                                resident.deathPlaceCode,
                                resident.deathPlaceAddress
                        )
                ).fetchOne();
    }
}
