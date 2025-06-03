package com.example.bank.mybank.service;
import com.example.bank.mybank.Exception.InsufficientBalanceException;
import com.example.bank.mybank.Exception.NotFoundException;
import com.example.bank.mybank.model.Account;
import com.example.bank.mybank.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account){
        return accountRepository.save(account);
    }

    public Optional getAccount(Long id){
        return accountRepository.findById(id);
    }


    @Transactional
    public Account deposite(Long id, Double amount) throws Throwable {
        Account account= (Account) getAccount(id).orElseThrow(()->new NotFoundException("Account Not Found"));
        account.setBalance((account.getBalance()+amount));
        return account;
    }

    @Transactional
    public Account withdraw(Long id, Double amount) throws Throwable {
        Account account= (Account) getAccount(id).orElseThrow(()->new NotFoundException("Account Not Found"));
        if(account.getBalance()<amount){
            throw new InsufficientBalanceException("Insufficient Balace");
        }
        account.setBalance(account.getBalance()-amount);
        return account;
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
