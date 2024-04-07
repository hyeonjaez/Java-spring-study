package com.example.accountbackend.service;


import com.example.accountbackend.domain.Account;
import java.util.List;

public interface AccountService {
    Account getAccount(Long id);
    Account createAccount(Account account);
    List<Account> getAccounts();
    void deleteAccount(Long id);

    boolean existAccount(Long id);
}
