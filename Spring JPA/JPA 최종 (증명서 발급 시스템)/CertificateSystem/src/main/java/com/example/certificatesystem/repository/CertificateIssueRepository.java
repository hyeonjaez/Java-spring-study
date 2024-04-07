package com.example.certificatesystem.repository;

import com.example.certificatesystem.domain.CertificateIssueDto;
import com.example.certificatesystem.domain.CertificateIssueResidentDto;
import com.example.certificatesystem.entity.CertificateIssue;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
    List<CertificateIssue> findAll();

    @Query("SELECT new com.example.certificatesystem.domain.CertificateIssueResidentDto(ci.certificateConfirmationNumber, ci.certificateIssueDate) FROM Resident r INNER JOIN CertificateIssue ci ON r.residentSerialNumber = ci.residentSerialNumber WHERE r.residentSerialNumber = :residentSerialNumber AND ci.certificateTypeCode = :certificateTypeCode")
    CertificateIssueResidentDto getCertificateConfirmationNumbersAndIssueDates(
            @Param("residentSerialNumber") Integer residentSerialNumber,
            @Param("certificateTypeCode") String certificateTypeCode);
}

