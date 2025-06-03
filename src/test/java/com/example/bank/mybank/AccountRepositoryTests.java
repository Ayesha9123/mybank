package com.example.bank.mybank;

import com.example.bank.mybank.model.Account;
import com.example.bank.mybank.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
public class AccountRepositoryTests {

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void saveAccountTest() {

        Account account = Account.builder().
                accountHolderName("Sreya").balance(20000).build();
        accountRepository.save(account);
        Assertions.assertThat(account.getId()).isGreaterThan(0);
    }
}
