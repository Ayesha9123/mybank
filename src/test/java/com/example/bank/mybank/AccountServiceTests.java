package com.example.bank.mybank;

import com.example.bank.mybank.Exception.InsufficientBalanceException;
import com.example.bank.mybank.Exception.NotFoundException;
import com.example.bank.mybank.model.Account;
import com.example.bank.mybank.repository.AccountRepository;
import com.example.bank.mybank.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {
    private Account account;

    @Mock
    AccountRepository accountRepository;


    @InjectMocks
    AccountService accountService;

    @Test
    public void createAccountTest(){
        Account account = new Account();
        account.setId(1L);
        account.setAccountHolderName("Ayesha Begum");
        account.setBalance(5000);
        Mockito.when(accountRepository.save(account)).thenReturn(account);
        Account savedAccount = accountService.createAccount(account);
        System.out.println("My First Unit test");
        Assertions.assertEquals(account.getId(), savedAccount.getId());
        Assertions.assertEquals(account.getAccountHolderName(),savedAccount.getAccountHolderName());
    Assertions.assertEquals(account.getBalance(),savedAccount.getBalance());

    }

    @Test
    public void getAccountTest(){
        Account account = new Account();
        account.setId(1L);
        account.setAccountHolderName("Ayesha Begum");
        account.setBalance(5000);
        Mockito.when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        Account savedAccount = accountService.createAccount(account);
        Optional retrivedAccount = accountService.getAccount(account.getId());
        Account retAcct = (Account) retrivedAccount.get();
        Assertions.assertEquals(retAcct.getId(),account.getId());
    }

    @Test
    public void depositeTest() throws Throwable {
    Mockito.when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

        Account savedAccount = accountService.deposite(account.getId(), 1000.0);
       // assertThrows(NotFoundException.class, () -> accountRepository.findById(8l));
        Assertions.assertEquals(savedAccount.getId(), account.getId());
        Assertions.assertEquals(savedAccount.getBalance(),account.getBalance());
    }

    @Test
    public void depositeTestException() throws Throwable {
        account.setId(2L);
        RuntimeException exception = assertThrows(NotFoundException.class,
                () -> accountService.deposite(account.getId(),1000.0));
        assertEquals("Account Not Found", exception.getMessage());
    }

    @Test
    public void withdrawTest() throws Throwable {
        Mockito.when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        Account savedAccount = accountService.withdraw(account.getId(), 2000.0);
        Assertions.assertEquals(savedAccount.getId(), account.getId());
        Assertions.assertEquals(savedAccount.getBalance(),account.getBalance());
    }

    @Test
    public void withdrawTestException() throws Throwable {

        Mockito.when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        RuntimeException exception = assertThrows(InsufficientBalanceException.class,
                () -> accountService.withdraw(account.getId(),10000.0));
        assertEquals("Insufficient Balace", exception.getMessage());
        account.setId(2L);
        RuntimeException exceptionNotFound = assertThrows(NotFoundException.class,
                () -> accountService.withdraw(account.getId(),1000.0));
        assertEquals("Account Not Found", exceptionNotFound.getMessage());
    }

    @Test
    public void deleteTest() throws Throwable {
        Mockito.doNothing().when(accountRepository).deleteById(account.getId());
        accountService.deleteAccount(account.getId());
        Mockito.verify(accountRepository,Mockito.times(1)).deleteById(account.getId());
    }


    @BeforeEach
    public void createDummyAccount(){
        account= new Account();
        account.setId(1L);
        account.setAccountHolderName("Ayesha Begum");
        account.setBalance(5000);
    }
}
