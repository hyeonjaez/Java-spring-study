package com.example.certificatesystem.controller;

import com.example.certificatesystem.domain.BirthDeathReportResidentDto;
import com.example.certificatesystem.domain.BirthResidentDto;
import com.example.certificatesystem.domain.BirthResidentParentsDto;
import com.example.certificatesystem.domain.CertificateIssueResidentDto;
import com.example.certificatesystem.domain.DeathResidentDto;
import com.example.certificatesystem.domain.FamilyRelationResidentDto;
import com.example.certificatesystem.domain.HouseholdCompositionResidentDto;
import com.example.certificatesystem.domain.HouseholdDto;
import com.example.certificatesystem.domain.HouseholdMovementAddressDto;
import com.example.certificatesystem.service.HouseholdService;
import com.example.certificatesystem.service.ResidentService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CertificateController {
    private final HouseholdService householdService;
    private final ResidentService residentService;

    public CertificateController(HouseholdService householdService, ResidentService residentService) {
        this.householdService = householdService;
        this.residentService = residentService;
    }

    @GetMapping("/report/ResidentRegistration/{residentNumber}")
    public String residentRegistration(@PathVariable Integer residentNumber, Model model) {
        HouseholdDto householdDtoByResidentSerialNumber =
                householdService.getHouseholdDtoByResidentSerialNumber(residentNumber);
        Integer householdSerialNumber = householdDtoByResidentSerialNumber.getHouseholdSerialNumber();

        List<HouseholdCompositionResidentDto> householdCompositionResidentList =
                householdService.getHouseholdCompositionResidentList(householdSerialNumber);

        List<HouseholdMovementAddressDto> householdMovementAddresssDtoList =
                householdService.getHouseholdMovementAddresssDtoList(householdSerialNumber);

        CertificateIssueResidentDto certificate =
                residentService.getCertificateConfirmationNumbersAndIssueDates(residentNumber, "주민등록등본");

        model.addAttribute("householdDto", householdDtoByResidentSerialNumber);
        model.addAttribute("compositionResidentList", householdCompositionResidentList);
        model.addAttribute("movementAddressDtoList", householdMovementAddresssDtoList);
        model.addAttribute("certificate", certificate);

        return "registration";
    }

    @GetMapping("/report/FamilyRelationship/{residentNumber}")
    public String residentFamilyRelationship(@PathVariable Integer residentNumber, Model model) {
        List<FamilyRelationResidentDto> familyRelationResidentDtoList =
                residentService.familyRelationResidentDtoList(residentNumber);

        CertificateIssueResidentDto certificateIssueResidentDto =
                residentService.getCertificateConfirmationNumbersAndIssueDates(residentNumber, "가족관계증명서");
        String address = residentService.getAddressById(residentNumber);

        model.addAttribute("familyList", familyRelationResidentDtoList);
        model.addAttribute("certificate", certificateIssueResidentDto);
        model.addAttribute("address", address);
        return "familyRelationship";
    }

    @GetMapping("/report/Birth/{residentNumber}")
    public String residentBirthReport(@PathVariable Integer residentNumber, Model model) {
        BirthResidentDto birthResident = residentService.findResidentDto("출생", residentNumber);

        List<BirthResidentParentsDto> residentParentList = residentService.findResidentParent(residentNumber);

        BirthDeathReportResidentDto birthDeathReportResidentDto =
                residentService.birthDeathReportResident(residentNumber);

        model.addAttribute("birthResident", birthResident);
        model.addAttribute("residentParentList", residentParentList);
        model.addAttribute("birthReportResident", birthDeathReportResidentDto);
        return "birthReport";
    }

    @GetMapping("/report/death/{residentNumber}")
    public String residentDeathReport(@PathVariable Integer residentNumber, Model model) {
        DeathResidentDto deathResidentDto = residentService.findDeathResident("사망", residentNumber);

        BirthDeathReportResidentDto birthDeathReportResidentDto =
                residentService.birthDeathReportResident(residentNumber);

        model.addAttribute("deathResident", deathResidentDto);
        model.addAttribute("deathList", birthDeathReportResidentDto);
        return "deathReport";
    }
}
