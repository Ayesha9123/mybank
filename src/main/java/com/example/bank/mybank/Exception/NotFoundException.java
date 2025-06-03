package com.example.bank.mybank.Exception;

public class NotFoundException extends RuntimeException {
    private String msg;

    public NotFoundException(){}

    public NotFoundException(String msg){
        super(msg);
        this.msg=msg;
    }
}
