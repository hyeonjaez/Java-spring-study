package com.example.certificatesystem.repository;

import com.example.certificatesystem.entity.HouseholdCompositionResident;
import com.example.certificatesystem.repository.custom.HouseholdCompositionResidentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdCompositionResidentRepository extends HouseholdCompositionResidentRepositoryCustom, JpaRepository<HouseholdCompositionResident, Integer> {
}
