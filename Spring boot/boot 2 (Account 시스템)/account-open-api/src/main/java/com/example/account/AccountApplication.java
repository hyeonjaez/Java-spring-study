package com.example.account;



import com.example.account.domain.Account;
import com.example.account.domain.AccountClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ConfigurationPropertiesScan
@SpringBootApplication
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

	@Autowired
	AccountClientService accountClientService;


	@Bean
	ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener() {
		return event -> {
			accountClientService.createAccount(new Account(80L, "정재현", "110-111", 1000000000));
			accountClientService.getAccounts()
					.forEach(it -> log.info("multi: {}", it));
		};
	}

}
