package com.example.certificatesystem.repository.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.example.certificatesystem.config.RootConfig;
import com.example.certificatesystem.config.WebConfig;
import com.example.certificatesystem.domain.HouseholdMovementAddressDto;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class HouseholdMovementAddressRepositoryImplTest {
    @Autowired
    HouseholdMovementAddressRepositoryImpl householdMovementAddressRepository;

    @Test
    void getHouseholdMovementAddressDtoListTest(){
        List<HouseholdMovementAddressDto> householdMovementAddresssDtoList =
                householdMovementAddressRepository.getHouseholdMovementAddresssDtoList(1);
        Assertions.assertEquals("2007-10-31", householdMovementAddresssDtoList.get(0).getHouseMovementReportDate());
    }
}