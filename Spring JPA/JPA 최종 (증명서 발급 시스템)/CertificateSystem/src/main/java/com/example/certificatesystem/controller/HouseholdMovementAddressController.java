package com.example.certificatesystem.controller;

import com.example.certificatesystem.domain.HouseholdMovementAddressModify;
import com.example.certificatesystem.domain.HouseholdMovementAddressRequest;
import com.example.certificatesystem.entity.Household;
import com.example.certificatesystem.entity.HouseholdMovementAddress;
import com.example.certificatesystem.service.HouseholdService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HouseholdMovementAddressController {
    HouseholdService householdService;

    public HouseholdMovementAddressController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping("/household/{householdSerialNumber}/movement")
    public ResponseEntity<HouseholdMovementAddress> registryHouseholdMovementAddress(
            @Valid @RequestBody HouseholdMovementAddressRequest householdMovementAddressRequest,
            @PathVariable Integer householdSerialNumber) {
        Household household = householdService.findHousehold(householdSerialNumber);

        HouseholdMovementAddress movementAddress = new HouseholdMovementAddress();
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk();
        pk.setHouseholdSerialNumber(householdSerialNumber);
        pk.setHouseMovementReportDate(householdMovementAddressRequest.getHouseMovementReportDate());

        movementAddress.setPk(pk);
        movementAddress.setHouseMovementAddress(householdMovementAddressRequest.getHouseMovementAddress());
        movementAddress.setLastAddressYOrN(householdMovementAddressRequest.getLastAddressYOrN());
        movementAddress.setHousehold(household);

        householdService.saveHouseholdMovementAddress(movementAddress);
        return ResponseEntity.ok().body(movementAddress);
    }

    @PutMapping("/household/{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<HouseholdMovementAddress> modifyHouseholdMovementAddress(
            @PathVariable Integer householdSerialNumber,
            @PathVariable String reportDate,
            @Valid @RequestBody HouseholdMovementAddressModify householdMovementAddressModify) {

        HouseholdMovementAddress householdMovementAddress =
                householdService.findHouseholdMovementAddress(reportDate, householdSerialNumber);

        householdMovementAddress.setHouseMovementAddress(householdMovementAddressModify.getHouseMovementAddress());
        householdMovementAddress.setLastAddressYOrN(householdMovementAddressModify.getLastAddressYOrN());

        householdService.saveHouseholdMovementAddress(householdMovementAddress);

        return ResponseEntity.ok().body(householdMovementAddress);

    }

    @DeleteMapping("/household/{householdSerialNumber}/movement/{reportDate}")
    public void deleteHouseholdMovementAddress(@PathVariable Integer householdSerialNumber,
                                               @PathVariable String reportDate) {
        householdService.deleteHouseholdMovementAddress(reportDate, householdSerialNumber);
    }
}
