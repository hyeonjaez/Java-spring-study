package com.example.certificatesystem.repository.impl;


import com.example.certificatesystem.config.RootConfig;
import com.example.certificatesystem.config.WebConfig;
import com.example.certificatesystem.domain.HouseholdCompositionResidentDto;
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
class HouseholdCompositionResidentRepositoryImplTest {
    @Autowired
    private HouseholdCompositionResidentRepositoryImpl householdCompositionResidentRepository;

    @Test
    void getHouseholdCompositionResidentListTest(){
        List<HouseholdCompositionResidentDto> householdCompositionResidentList =
                householdCompositionResidentRepository.getHouseholdCompositionResidentList(1);

        Assertions.assertEquals("본인",householdCompositionResidentList.get(0).getHouseholdRelationshipCode());

    }

}