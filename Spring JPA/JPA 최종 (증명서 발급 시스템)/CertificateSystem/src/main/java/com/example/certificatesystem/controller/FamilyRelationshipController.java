package com.example.certificatesystem.controller;

import com.example.certificatesystem.domain.FamilyRelationshipOnlyRelationshipRequest;
import com.example.certificatesystem.domain.FamilyRelationshipRequest;
import com.example.certificatesystem.entity.FamilyRelationship;
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
public class FamilyRelationshipController {

    ResidentService residentService;

    public FamilyRelationshipController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping("/residents/{serialNumber}/relationship")
    public ResponseEntity<FamilyRelationship> registryFamilyRelationship(
            @Valid @RequestBody FamilyRelationshipRequest familyRelationshipRequest,
            @PathVariable Integer serialNumber) {
        Resident resident = residentService.findByResident(serialNumber);

        FamilyRelationship familyRelationship = new FamilyRelationship();

        FamilyRelationship.Pk pk = new FamilyRelationship.Pk();

        pk.setBaseResidentSerialNumber(resident.getResidentSerialNumber());
        pk.setFamilyResidentSerialNumber(familyRelationshipRequest.getFamilySerialNumber());
        familyRelationship.setPk(pk);

        familyRelationship.setFamilyRelationshipCode(familyRelationshipRequest.getRelationship());
        familyRelationship.setResident(resident);

        FamilyRelationship saveFamilyRelationship = residentService.saveFamilyRelationship(familyRelationship);
        return ResponseEntity.ok().body(saveFamilyRelationship);
    }

    @PutMapping("/residents/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<FamilyRelationship> modifyFamilyRelationship(@PathVariable Integer serialNumber,
                                                                       @PathVariable Integer familySerialNumber,
                                                                       @Valid @RequestBody
                                                                       FamilyRelationshipOnlyRelationshipRequest familyRelationshipOnlyRelationshipRequest) {
        FamilyRelationship familyRelationship =
                residentService.findFamilyRelationship(serialNumber, familySerialNumber);
        familyRelationship.setFamilyRelationshipCode(familyRelationshipOnlyRelationshipRequest.getRelationShip());

        residentService.saveFamilyRelationship(familyRelationship);
        return ResponseEntity.ok().body(familyRelationship);

    }

    @DeleteMapping("/residents/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<Void> deleteFamilyRelationship(@PathVariable Integer serialNumber,
                                                         @PathVariable Integer familySerialNumber) {
        residentService.deleteFamilyRelationship(serialNumber, familySerialNumber);

        return ResponseEntity.noContent().build();
    }

}
