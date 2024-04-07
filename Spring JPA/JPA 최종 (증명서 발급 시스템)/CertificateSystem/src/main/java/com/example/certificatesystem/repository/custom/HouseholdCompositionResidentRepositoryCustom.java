package com.example.certificatesystem.repository.custom;

import com.example.certificatesystem.domain.HouseholdCompositionResidentDto;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HouseholdCompositionResidentRepositoryCustom {
    List<HouseholdCompositionResidentDto> getHouseholdCompositionResidentList(Integer residentSerialNumber);
}
