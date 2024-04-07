package com.example.certificatesystem.repository.custom;

import com.example.certificatesystem.domain.HouseholdDto;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HouseholdRepositoryCustom {
    HouseholdDto getHouseholdDtoByResidentSerialNumber(Integer residentSerialNumber);
}
