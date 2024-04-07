package com.example.certificatesystem.repository;

import com.example.certificatesystem.entity.BirthDeathReportResident;
import com.example.certificatesystem.repository.custom.BirthDeathReportResidentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BirthDeathReportResidentRepository
        extends BirthDeathReportResidentRepositoryCustom, JpaRepository<BirthDeathReportResident, Integer> {
    BirthDeathReportResident findBirthDeathReportResidentByPk_ResidentSerialNumber_AndPk_BirthDeathTypeCodeAndReportResidentSerialNumber(
            Integer residentSerialNumber, String birthDeathType, Integer reportResidentSerialNumber);

    void deleteBirthDeathReportResidentByPk_ResidentSerialNumberAndPk_BirthDeathTypeCodeAndReportResidentSerialNumber(
            Integer residentSerialNumber, String birthDeathType, Integer reportResidentSerialNumber);
}
