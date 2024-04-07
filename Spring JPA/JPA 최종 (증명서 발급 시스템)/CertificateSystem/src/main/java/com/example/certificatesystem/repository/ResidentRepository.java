package com.example.certificatesystem.repository;


import com.example.certificatesystem.entity.Resident;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    Optional<Resident> findByResidentSerialNumber(Integer residentSerialNumber);

//    ResidentDto findResidentByResidentSerialNumber(Integer residentSerialNumber);

    Page<Resident> findAll(Pageable pageable);

    List<Resident> findAll();

    @Query("SELECT r.residentSerialNumber, r.name, ci.certificateTypeCode FROM Resident r LEFT JOIN CertificateIssue ci ON r.residentSerialNumber = ci.residentSerialNumber")
    List<List<Object>> getResidentAndCertificateInfo();
//    @Query("SELECT r.residentSerialNumber, r.name, ci.certificateTypeCode FROM Resident r LEFT JOIN CertificateIssue ci ON r.residentSerialNumber = ci.residentSerialNumber")
//    Page<Object[]> getResidentAndCertificateInfoPageable(Pageable pageable);





}