package com.example.bank.mybank.Exception;

public class InsufficientBalanceException extends RuntimeException{
        private String msg;

    public InsufficientBalanceException(){}

    public InsufficientBalanceException(String msg){
            super(msg);
            this.msg=msg;
    }


}

