package com.example.bank.mybank.controller;

import com.example.bank.mybank.Exception.NotFoundException;
import com.example.bank.mybank.model.Account;
import com.example.bank.mybank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) throws Throwable {
        return (Account) accountService.getAccount(id).orElseThrow(()->
                new NotFoundException("Account Not Found"));
    }

    @PostMapping("{id}/deposite")
    public Account deposite(@PathVariable Long id,  @RequestBody Map<String, Double> request) throws Throwable {
        Double amount = request.get("amount");
        return accountService.deposite(id,amount);

    }
    @PostMapping("{id}/withdraw")
    public Account withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request) throws Throwable {
        Double amount = request.get("amount");
        return accountService.withdraw(id,amount);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable Long id){
        try {
            accountService.deleteAccount(id);
        }catch (NotFoundException e){
            throw new NotFoundException("Account Not Found to delete");
        }
    }
}
