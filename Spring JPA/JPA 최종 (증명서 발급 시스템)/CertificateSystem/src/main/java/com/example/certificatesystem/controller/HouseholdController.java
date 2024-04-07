package com.example.certificatesystem.controller;

import com.example.certificatesystem.domain.HouseholdRequest;
import com.example.certificatesystem.entity.Household;
import com.example.certificatesystem.entity.Resident;
import com.example.certificatesystem.service.HouseholdService;
import com.example.certificatesystem.service.ResidentService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HouseholdController {
    HouseholdService householdService;
    ResidentService residentService;

    public HouseholdController(HouseholdService householdService, ResidentService residentService) {
        this.householdService = householdService;
        this.residentService = residentService;
    }

    @PostMapping("/household")
    public ResponseEntity<Household> registryHousehold(@Valid @RequestBody HouseholdRequest householdRequest) {
        Resident resident = residentService.findByResident(householdRequest.getHouseholdResidentSerialNumber());

        Household household = new Household();

        household.setHouseholdSerialNumber(householdRequest.getHouseholdSerialNumber());
        household.setHouseholdResidentSerialNumber(householdRequest.getHouseholdResidentSerialNumber());
        household.setHouseholdCompositionDate(householdRequest.getHouseholdCompositionDate());
        household.setHouseholdCompositionReasonCode(householdRequest.getHouseholdCompositionReasonCode());
        household.setCurrentHouseMovementAddress(household.getCurrentHouseMovementAddress());
        household.setResident(resident);

        householdService.saveHousehold(household);
        return ResponseEntity.ok().body(household);
    }

    @DeleteMapping("/household/{householdSerialNumber}")
    public void deleteHousehold(@PathVariable Integer householdSerialNumber) {
        householdService.deleteHousehold(householdSerialNumber);
    }
}
