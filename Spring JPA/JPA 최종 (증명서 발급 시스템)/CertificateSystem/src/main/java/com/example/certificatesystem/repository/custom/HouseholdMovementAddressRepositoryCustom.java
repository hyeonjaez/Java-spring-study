package com.example.certificatesystem.repository.custom;

import com.example.certificatesystem.domain.HouseholdMovementAddressDto;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HouseholdMovementAddressRepositoryCustom {
    List<HouseholdMovementAddressDto> getHouseholdMovementAddresssDtoList(Integer householdSerialNumber);
}
