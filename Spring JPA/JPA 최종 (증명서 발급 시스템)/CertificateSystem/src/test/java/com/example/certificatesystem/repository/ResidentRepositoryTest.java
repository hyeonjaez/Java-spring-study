package com.example.certificatesystem.repository;

//import static jdk.internal.jrtfs.JrtFileAttributeView.AttrID.size;
import static org.junit.jupiter.api.Assertions.assertEquals;


import com.example.certificatesystem.config.RootConfig;
import com.example.certificatesystem.config.WebConfig;
import com.example.certificatesystem.domain.ResidentCertificateInfo;
import com.example.certificatesystem.entity.Resident;
import com.example.certificatesystem.service.ResidentService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
class ResidentRepositoryTest {


    @Autowired
    ResidentRepository residentRepository;

    @Autowired
    ResidentService residentService;

    @Test
    void findByResidentSerialNumberTest() {
        Resident resident = residentRepository.findByResidentSerialNumber(1).orElse(null);
        assertEquals("남길동", resident.getName());
    }


}