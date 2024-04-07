package com.example.certificatesystem.service;


import com.example.certificatesystem.domain.BirthDeathReportResidentDto;
import com.example.certificatesystem.domain.BirthResidentDto;
import com.example.certificatesystem.domain.BirthResidentParentsDto;
import com.example.certificatesystem.domain.CertificateIssueDto;
import com.example.certificatesystem.domain.CertificateIssueResidentDto;
import com.example.certificatesystem.domain.DeathResidentDto;
import com.example.certificatesystem.domain.FamilyRelationResidentDto;
import com.example.certificatesystem.domain.ResidentCertificateInfo;
import com.example.certificatesystem.domain.ResidentDto;
import com.example.certificatesystem.entity.BirthDeathReportResident;
import com.example.certificatesystem.entity.FamilyRelationship;
import com.example.certificatesystem.entity.Resident;
import java.util.List;

public interface ResidentService {
    Resident save(Resident resident);

    Resident findByResident(Integer residentSerialNumber);

    List<ResidentDto> residentDtoList();

    List<CertificateIssueDto> certificateIssueDtoList();

    FamilyRelationship saveFamilyRelationship(FamilyRelationship familyRelationship);

    FamilyRelationship findFamilyRelationship(Integer baseResidentSerialNumber, Integer familyResidentSerialNumber);

    void deleteFamilyRelationship(Integer baseResidentSerialNumber, Integer familyResidentSerialNumber);

    List<ResidentCertificateInfo> getResidentsPage();

    BirthDeathReportResident saveBirthDeathReport(BirthDeathReportResident birthDeathReportResident);

    BirthDeathReportResident findBirthDeathReportResident(Integer residentSerialNumber, String birthDeathType,
                                                          Integer reportResidentSerialNumber);

    void deleteBirthDeathReport(Integer residentSerialNumber, String birthDeathType,
                                Integer reportResidentSerialNumber);

    CertificateIssueResidentDto getCertificateConfirmationNumbersAndIssueDates(Integer residentSerialNumber,
                                                                               String certificateTypeCode);

    List<FamilyRelationResidentDto> familyRelationResidentDtoList(Integer residentSerialNumber);

    String getAddressById(Integer residentSerialNumber);

    BirthResidentDto findResidentDto(String birthDeathTypeCode, Integer residentNumber);

    List<BirthResidentParentsDto> findResidentParent(Integer residentNumber);

    BirthDeathReportResidentDto birthDeathReportResident(Integer residentNumber);

    DeathResidentDto findDeathResident(String birthDeathTypeCode, Integer residentNumber);


}
