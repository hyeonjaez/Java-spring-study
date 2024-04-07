package com.example.certificatesystem.repository.custom;

import com.example.certificatesystem.domain.BirthDeathReportResidentDto;
import com.example.certificatesystem.domain.BirthResidentDto;
import com.example.certificatesystem.domain.BirthResidentParentsDto;
import com.example.certificatesystem.domain.DeathResidentDto;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BirthDeathReportResidentRepositoryCustom {
    BirthResidentDto findResidentDto(String birthDeathTypeCode, Integer residentSerialNumber);

    List<BirthResidentParentsDto> findResidentParent(Integer residentSerialNumber);

    BirthDeathReportResidentDto birthReportResident(Integer residentSerialNumber);

    DeathResidentDto findDeathResidentDto(String birthDeathTypeCode, Integer residentSerialNumber);
}