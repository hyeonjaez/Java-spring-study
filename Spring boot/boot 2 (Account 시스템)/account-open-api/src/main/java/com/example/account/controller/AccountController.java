package com.example.account.controller;

import com.example.account.domain.Account;
import com.example.account.domain.AccountAdaptor;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountAdaptor accountAdaptor;

    public AccountController(AccountAdaptor accountAdaptor) {
        this.accountAdaptor = accountAdaptor;
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable("id") Long id) {
        return accountAdaptor.getAccount(id);
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountAdaptor.getAccounts();
    }

    @PostMapping("/accounts")
    public Account createAccounts(@RequestBody Account account) {
        return accountAdaptor.createAccount(account);
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable("id") Long id) {
        accountAdaptor.deleteAccount(id);
    }
}