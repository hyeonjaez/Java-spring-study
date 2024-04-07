package com.example.accountbackend.Repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.accountbackend.domain.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void testFindAll(){
        Account account = new Account(45L,"jaehyeon","123-321",1000);
        entityManager.merge(account);

        Account account1 = accountRepository.findById(45L).orElse(null);
        Assertions.assertEquals(account1.getNumber(), account.getNumber());
    }
}