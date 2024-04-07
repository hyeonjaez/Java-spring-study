package com.example.certificatesystem.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class FamilyRelationResidentDto {
    String familyRelationshipCode;
    String name;
    String birthDate;
    String residentRegistrationNumber;
    String genderCode;

    public FamilyRelationResidentDto(String name, String birthDate, String residentRegistrationNumber,
                                     String genderCode) {
        this.familyRelationshipCode = "본인";
        this.name = name;
        this.birthDate = birthDate;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.genderCode = genderCode;
    }
}