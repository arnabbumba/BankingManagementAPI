package com.bankingmanagement.exception;

public class BankDetailsNotFound extends Exception {
    public BankDetailsNotFound(){
        super();
    }
    public BankDetailsNotFound(String msg){
        super(msg);
    }
    public BankDetailsNotFound(String msg, Throwable throwable){
        super(msg,throwable);
    }
}
