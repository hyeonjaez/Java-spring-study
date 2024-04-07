package com.example.certificatesystem.controller;


import com.example.certificatesystem.domain.ResidentRequest;
import com.example.certificatesystem.entity.Resident;
import com.example.certificatesystem.service.ResidentService;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResidentController {
    ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping("/residents")
    public ResponseEntity<Resident> registerResident(@Valid @RequestBody ResidentRequest residentRequest) {
        Resident resident = new Resident(residentRequest.getResidentSerialNumber(),
                residentRequest.getName(),
                residentRequest.getResidentRegistrationNumber(),
                residentRequest.getGenderCode(),
                residentRequest.getBirthDate(),
                residentRequest.getBirthPlaceCode(),
                residentRequest.getRegistrationBaseAddress(),
                residentRequest.getDeathDate(),
                residentRequest.getDeathPlaceCode(),
                residentRequest.getDeathPlaceAddress());

        residentService.save(resident);
        return ResponseEntity.ok().body(resident);
    }

    @PutMapping("/residents/{serialNumber}")
    public ResponseEntity<Resident> modifyResident(@PathVariable Integer serialNumber,
                                                     @Valid @RequestBody ResidentRequest residentRequest) {
        Resident targetResident = residentService.findByResident(serialNumber);
        if (Objects.nonNull(targetResident)) {
            Resident resident = new Resident(residentRequest.getResidentSerialNumber(),
                    residentRequest.getName(),
                    residentRequest.getResidentRegistrationNumber(),
                    residentRequest.getGenderCode(),
                    residentRequest.getBirthDate(),
                    residentRequest.getBirthPlaceCode(),
                    residentRequest.getRegistrationBaseAddress(),
                    residentRequest.getDeathDate(),
                    residentRequest.getDeathPlaceCode(),
                    residentRequest.getDeathPlaceAddress());
            residentService.save(resident);

            return ResponseEntity.ok().body(resident);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
