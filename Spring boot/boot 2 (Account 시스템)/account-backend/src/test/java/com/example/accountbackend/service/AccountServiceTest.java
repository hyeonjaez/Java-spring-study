package com.example.accountbackend.service;

import com.example.accountbackend.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @Test
    public void test() {
        Account account = new Account(123L, "jaehyeon", "11002-123", 19000);

        accountService.createAccount(account);
    }
}