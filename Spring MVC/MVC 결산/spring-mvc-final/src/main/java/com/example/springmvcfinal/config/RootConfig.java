package com.example.springmvcfinal.config;

import com.example.springmvcfinal.Base;
import com.example.springmvcfinal.domain.InquiryCategory;
import com.example.springmvcfinal.domain.UserCategory;
import com.example.springmvcfinal.repository.InquiryRepository;
import com.example.springmvcfinal.repository.InquiryRepositoryImpl;
import com.example.springmvcfinal.repository.UserInquiryRepository;
import com.example.springmvcfinal.repository.UserInquiryRepositoryImpl;
import com.example.springmvcfinal.repository.UserRepository;
import com.example.springmvcfinal.repository.UserRepositoryImpl;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = {@ComponentScan.Filter(Controller.class)})

public class RootConfig {


    @Bean
    public UserRepository userRepository() {
        UserRepository userRepository = new UserRepositoryImpl(new HashMap<>());
        userRepository.register("user", "qwer", "유저", UserCategory.USER);
        userRepository.register("admin", "qwer", "관리자", UserCategory.ADMIN);
        return userRepository;
    }

    @Bean
    public InquiryRepository inquiryRepository() {
        InquiryRepository inquiryRepository = new InquiryRepositoryImpl(new HashMap<>());

        inquiryRepository.register("test1", InquiryCategory.COMPLAINT_SUBMISSION, "1test입니다",
                LocalDateTime.now());
        inquiryRepository.register("test2", InquiryCategory.OTHER_INQUIRIES, "2test입니다",
                LocalDateTime.now().minusDays(1));
        inquiryRepository.register("test3", InquiryCategory.PROPOSAL, "3test입니다",
                LocalDateTime.now().plusDays(2));
        inquiryRepository.register("test4", InquiryCategory.COMPLIMENT, "4test입니다",
                LocalDateTime.now());
        return inquiryRepository;
    }


    @Bean
    @Autowired
    public UserInquiryRepository userInquiryRepository(InquiryRepository inquiryRepository) {
        UserInquiryRepository userInquiryRepository =
                new UserInquiryRepositoryImpl(inquiryRepository, new HashMap<>());
        userInquiryRepository.addInquiry("user", 1);
        userInquiryRepository.addInquiry("user", 2);
        userInquiryRepository.addInquiry("user", 3);
        userInquiryRepository.addInquiry("user", 4);

        return userInquiryRepository;
    }
}
