package com.example.certificatesystem.repository.impl;

import com.example.certificatesystem.config.RootConfig;
import com.example.certificatesystem.config.WebConfig;
import com.example.certificatesystem.domain.BirthDeathReportResidentDto;
import com.example.certificatesystem.domain.BirthResidentDto;
import com.example.certificatesystem.domain.BirthResidentParentsDto;
import com.example.certificatesystem.domain.DeathResidentDto;
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
class BirthDeathReportResidentRepositoryImplTest {
    @Autowired
    BirthDeathReportResidentRepositoryImpl birthDeathReportResidentRepository;

    @Test
    void findResidentDtoTest() {
        BirthResidentDto birthResidentDto = birthDeathReportResidentRepository.findResidentDto("출생", 7);

        Assertions.assertEquals("2012-03-17", birthResidentDto.getBirthDeathReportDate());
    }

    @Test
    void findResidentParentTest() {
        List<BirthResidentParentsDto> residentParent = birthDeathReportResidentRepository.findResidentParent(7);
        residentParent.stream().forEach(System.out::println);
        Assertions.assertEquals("남기준", residentParent.get(0).getName());
    }


    @Test
    void birthReportResidentTest() {
        BirthDeathReportResidentDto birthDeathReportResidentDto =
                birthDeathReportResidentRepository.birthReportResident(7);
        Assertions.assertEquals("남기준", birthDeathReportResidentDto.getName());
    }

    @Test
    void findDeathResidentTest() {
        DeathResidentDto deathResidentDto = birthDeathReportResidentRepository.findDeathResidentDto("사망", 1);
        Assertions.assertEquals("2020-05-02", deathResidentDto.getBirthDeathReportDate());
    }
}