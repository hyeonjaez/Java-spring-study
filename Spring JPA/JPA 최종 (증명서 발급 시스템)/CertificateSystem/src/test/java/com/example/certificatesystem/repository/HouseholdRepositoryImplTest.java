package com.example.certificatesystem.repository;

import com.example.certificatesystem.config.RootConfig;
import com.example.certificatesystem.config.WebConfig;
import com.example.certificatesystem.domain.HouseholdDto;
import com.example.certificatesystem.repository.impl.HouseholdRepositoryImpl;
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
class HouseholdRepositoryImplTest {

    @Autowired
    private HouseholdRepositoryImpl householdRepositoryImpl;

    @Test
    void getHouseholdDtoByResidentSerialNumberTest() {
        HouseholdDto householdDtoByResidentSerialNumber =
                householdRepositoryImpl.getHouseholdDtoByResidentSerialNumber(4);
        Assertions.assertEquals("남기준", householdDtoByResidentSerialNumber.getName());
    }
}