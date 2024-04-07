package com.example.certificatesystem.service;


import com.example.certificatesystem.domain.BirthDeathReportResidentDto;
import com.example.certificatesystem.domain.BirthResidentDto;
import com.example.certificatesystem.domain.BirthResidentParentsDto;
import com.example.certificatesystem.domain.CertificateIssueDto;
import com.example.certificatesystem.domain.CertificateIssueResidentDto;
import com.example.certificatesystem.domain.DeathResidentDto;
import com.example.certificatesystem.domain.FamilyRelationResidentDto;
import com.example.certificatesystem.domain.ResidentCertificateIdAndName;
import com.example.certificatesystem.domain.ResidentCertificateInfo;
import com.example.certificatesystem.domain.ResidentDto;
import com.example.certificatesystem.entity.BirthDeathReportResident;
import com.example.certificatesystem.entity.FamilyRelationship;
import com.example.certificatesystem.entity.Resident;
import com.example.certificatesystem.repository.BirthDeathReportResidentRepository;
import com.example.certificatesystem.repository.CertificateIssueRepository;
import com.example.certificatesystem.repository.FamilyRelationshipRepository;
import com.example.certificatesystem.repository.ResidentRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ResidentServiceImpl implements ResidentService {
    ResidentRepository residentRepository;
    FamilyRelationshipRepository familyRelationshipRepository;
    CertificateIssueRepository certificateIssueRepository;
    BirthDeathReportResidentRepository birthDeathReportResidentRepository;

    public ResidentServiceImpl(ResidentRepository residentRepository,
                               FamilyRelationshipRepository familyRelationshipRepository,
                               CertificateIssueRepository certificateIssueRepository,
                               BirthDeathReportResidentRepository birthDeathReportResidentRepository) {
        this.residentRepository = residentRepository;
        this.familyRelationshipRepository = familyRelationshipRepository;
        this.certificateIssueRepository = certificateIssueRepository;
        this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
    }

    @Override
    public Resident save(Resident resident) {
        return residentRepository.save(resident);
    }

    @Override
    public List<ResidentDto> residentDtoList() {
        return residentRepository.findAll()
                .stream()
                .map(resident -> new ResidentDto() {
                    @Override
                    public Integer getResidentSerialNumber() {
                        return resident.getResidentSerialNumber();
                    }

                    @Override
                    public String getName() {
                        return resident.getName();
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CertificateIssueDto> certificateIssueDtoList() {
        return certificateIssueRepository.findAll()
                .stream()
                .map(certificateIssue -> new CertificateIssueDto() {
                    @Override
                    public Integer getResidentSerialNumber() {
                        return certificateIssue.getResidentSerialNumber();
                    }

                    @Override
                    public String getCertificateTypeCode() {
                        return certificateIssue.getCertificateTypeCode();
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public Resident findByResident(Integer residentSerialNumber) {
        return residentRepository.findByResidentSerialNumber(residentSerialNumber).orElse(null);
    }

    @Override
    public FamilyRelationship saveFamilyRelationship(FamilyRelationship familyRelationship) {
        return familyRelationshipRepository.save(familyRelationship);
    }

    @Override
    public FamilyRelationship findFamilyRelationship(Integer baseResidentSerialNumber,
                                                     Integer familyResidentSerialNumber) {
        return familyRelationshipRepository.findFamilyRelationshipByPk_BaseResidentSerialNumberAndPk_FamilyResidentSerialNumber(
                baseResidentSerialNumber, familyResidentSerialNumber);
    }

    @Override
    public void deleteFamilyRelationship(Integer baseResidentSerialNumber, Integer familyResidentSerialNumber) {
        familyRelationshipRepository.deleteByPk_BaseResidentSerialNumberAndPk_FamilyResidentSerialNumber(
                baseResidentSerialNumber, familyResidentSerialNumber);
    }

    @Override
    public List<ResidentCertificateInfo> getResidentsPage() {
//        Pageable pageable = PageRequest.of(page, size);
        List<List<Object>> resultList = residentRepository.getResidentAndCertificateInfo();

        return resultList.stream()
                .collect(Collectors.groupingBy(
                        row -> new ResidentCertificateIdAndName(
                                (Integer) row.get(0),
                                (String) row.get(1)),
                        Collectors.mapping(row -> (String) row.get(2), Collectors.toList())
                ))
                .entrySet().stream()
                .map(entry -> new ResidentCertificateInfo(
                        entry.getKey().getResidentSerialNumber(),
                        entry.getKey().getName(),
                        entry.getValue())).collect(Collectors.toList());
    }

    @Override
    public BirthDeathReportResident saveBirthDeathReport(BirthDeathReportResident birthDeathReportResident) {

        return birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    @Override
    public BirthDeathReportResident findBirthDeathReportResident(Integer residentSerialNumber, String birthDeathType,
                                                                 Integer reportResidentSerialNumber) {
        return birthDeathReportResidentRepository.findBirthDeathReportResidentByPk_ResidentSerialNumber_AndPk_BirthDeathTypeCodeAndReportResidentSerialNumber(
                residentSerialNumber, birthDeathType, reportResidentSerialNumber);
    }

    @Override
    public void deleteBirthDeathReport(Integer residentSerialNumber, String birthDeathType,
                                       Integer reportResidentSerialNumber) {
        birthDeathReportResidentRepository.deleteBirthDeathReportResidentByPk_ResidentSerialNumberAndPk_BirthDeathTypeCodeAndReportResidentSerialNumber(
                residentSerialNumber, birthDeathType, reportResidentSerialNumber);
    }

    @Override
    public CertificateIssueResidentDto getCertificateConfirmationNumbersAndIssueDates(Integer residentSerialNumber,
                                                                                      String certificateTypeCode) {
        return certificateIssueRepository.getCertificateConfirmationNumbersAndIssueDates(residentSerialNumber,
                certificateTypeCode);
    }

    @Override
    public List<FamilyRelationResidentDto> familyRelationResidentDtoList(Integer residentSerialNumber) {
        List<FamilyRelationResidentDto> familyRelationshipInfo =
                familyRelationshipRepository.findFamilyRelationshipInfo(residentSerialNumber);

        FamilyRelationResidentDto meInfo = familyRelationshipRepository.findMeInfo(residentSerialNumber);
        familyRelationshipInfo.add(meInfo);

        return familyRelationshipInfo;
    }

    @Override
    public String getAddressById(Integer residentSerialNumber) {
        return familyRelationshipRepository.getAddressById(residentSerialNumber);
    }

    @Override
    public BirthResidentDto findResidentDto(String birthDeathTypeCode, Integer residentNumber) {
        return birthDeathReportResidentRepository.findResidentDto(birthDeathTypeCode, residentNumber);
    }

    @Override
    public List<BirthResidentParentsDto> findResidentParent(Integer residentNumber) {

        return birthDeathReportResidentRepository.findResidentParent(residentNumber);
    }

    @Override
    public BirthDeathReportResidentDto birthDeathReportResident(Integer residentNumber) {
        return birthDeathReportResidentRepository.birthReportResident(residentNumber);
    }

    @Override
    public DeathResidentDto findDeathResident(String birthDeathTypeCode, Integer residentNumber) {

        return birthDeathReportResidentRepository.findDeathResidentDto(birthDeathTypeCode, residentNumber);
    }
}
