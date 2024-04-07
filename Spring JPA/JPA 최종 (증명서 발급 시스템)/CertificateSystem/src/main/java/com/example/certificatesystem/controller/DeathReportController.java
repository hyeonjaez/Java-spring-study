package com.example.certificatesystem.controller;

import com.example.certificatesystem.domain.BirthDeathReportModifyRequest;
import com.example.certificatesystem.domain.BirthDeathReportRequest;
import com.example.certificatesystem.entity.BirthDeathReportResident;
import com.example.certificatesystem.entity.Resident;
import com.example.certificatesystem.service.ResidentService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeathReportController {
    ResidentService residentService;

    public DeathReportController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping("/residents/{serialNumber}/death")
    public ResponseEntity<BirthDeathReportResident> registryBirthDeathReportResident(
            @Valid @RequestBody BirthDeathReportRequest birthDeathReportRequest,
            @PathVariable Integer serialNumber) {
        Resident resident = residentService.findByResident(serialNumber);

        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setBirthDeathTypeCode("사망");
        pk.setResidentSerialNumber(serialNumber);

        birthDeathReportResident.setResident(resident);
        birthDeathReportResident.setPk(pk);
        birthDeathReportResident.setReportResidentSerialNumber(birthDeathReportRequest.getReportResidentSerialNumber());
        birthDeathReportResident.setBirthDeathReportDate(birthDeathReportRequest.getBirthDeathReportDate());
        birthDeathReportResident.setBirthReportQualificationsCode(
                birthDeathReportRequest.getBirthReportQualificationsCode());
        birthDeathReportResident.setDeathReportQualificationsCode(
                birthDeathReportRequest.getDeathReportQualificationsCode());
        birthDeathReportResident.setEmailAddress(birthDeathReportRequest.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(birthDeathReportRequest.getPhoneNumber());
        BirthDeathReportResident saveBirthDeathReportResident =
                residentService.saveBirthDeathReport(birthDeathReportResident);

        return ResponseEntity.ok().body(saveBirthDeathReportResident);

    }

    @PutMapping("/residents/{serialNumber}/death/{targetSerialNumber}")
    public ResponseEntity<BirthDeathReportResident> modifyDeathReportResident(
            @PathVariable Integer serialNumber,
            @PathVariable Integer targetSerialNumber,
            @Valid @RequestBody BirthDeathReportModifyRequest birthDeathReportModifyRequest) {

        BirthDeathReportResident birthDeathReportResident =
                residentService.findBirthDeathReportResident(targetSerialNumber, "사망", serialNumber);

        birthDeathReportResident.setBirthDeathReportDate(birthDeathReportModifyRequest.getBirthDeathReportDate());
        birthDeathReportResident.setBirthReportQualificationsCode(
                birthDeathReportModifyRequest.getDeathReportQualificationsCode());
        birthDeathReportResident.setDeathReportQualificationsCode(
                birthDeathReportModifyRequest.getDeathReportQualificationsCode());
        birthDeathReportResident.setEmailAddress(birthDeathReportModifyRequest.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(birthDeathReportModifyRequest.getPhoneNumber());

        residentService.saveBirthDeathReport(birthDeathReportResident);
        return ResponseEntity.ok().body(birthDeathReportResident);
    }

    @DeleteMapping("/residents/{serialNumber}/death/{targetSerialNumber}")
    public void deleteBirthDeathReportResident(@PathVariable Integer serialNumber,
                                               @PathVariable Integer targetSerialNumber) {
        residentService.deleteBirthDeathReport(targetSerialNumber, "사망", serialNumber);

    }
}
