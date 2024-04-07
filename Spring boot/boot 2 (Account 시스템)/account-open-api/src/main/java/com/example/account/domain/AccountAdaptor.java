package com.example.account.domain;

import java.util.List;

public interface AccountAdaptor {
    List<Account> getAccounts();

    Account getAccount(Long id);

    Account createAccount(Account account);

    void deleteAccount(Long id);
}