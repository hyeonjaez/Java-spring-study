package com.example.certificatesystem.repository;

import com.example.certificatesystem.domain.FamilyRelationResidentDto;
import com.example.certificatesystem.entity.FamilyRelationship;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {
    FamilyRelationship findFamilyRelationshipByPk_BaseResidentSerialNumberAndPk_FamilyResidentSerialNumber(
            Integer baseResidentSerialNumber, Integer familyResidentSerialNumber);

    void deleteByPk_BaseResidentSerialNumberAndPk_FamilyResidentSerialNumber(Integer baseResidentSerialNumber,
                                                                             Integer familyResidentSerialNumber);

    @Query("SELECT new com.example.certificatesystem.domain.FamilyRelationResidentDto(fr.familyRelationshipCode, r.name, r.birthDate, r.residentRegistrationNumber, r.genderCode) FROM Resident r INNER JOIN FamilyRelationship fr ON fr.pk.familyResidentSerialNumber = r.residentSerialNumber WHERE fr.pk.baseResidentSerialNumber IN (SELECT residentSerialNumber FROM Resident WHERE residentSerialNumber = :residentSerialNumber)")
    List<FamilyRelationResidentDto> findFamilyRelationshipInfo(
            @Param("residentSerialNumber") Integer residentSerialNumber);

    @Query("select new com.example.certificatesystem.domain.FamilyRelationResidentDto(r.name, r.birthDate, r.residentRegistrationNumber, r.genderCode) FROM Resident r WHERE r.residentSerialNumber = :residentSerialNumber")
    FamilyRelationResidentDto findMeInfo(@Param("residentSerialNumber") Integer residentSerialNumber);

    @Query("select r.registrationBaseAddress from Resident r where r.residentSerialNumber = :residentSerialNumber")
    String getAddressById(@Param("residentSerialNumber")Integer residentSerialNumber);
}
