package com.example.bank.mybank.controller;

import com.example.bank.mybank.Exception.ErrorResponse;
import com.example.bank.mybank.Exception.InsufficientBalanceException;
import com.example.bank.mybank.Exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountControllerAdvice {

    @ExceptionHandler(value= NotFoundException.class)
    public ErrorResponse handleNotFoundException(NotFoundException e){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(value = InsufficientBalanceException.class)
    public ErrorResponse handleInsufficientAmount(Exception e){
        return new ErrorResponse(HttpStatus.PAYMENT_REQUIRED.value(), e.getMessage());
    }
}
