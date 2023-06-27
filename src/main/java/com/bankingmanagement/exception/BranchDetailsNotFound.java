package com.bankingmanagement.exception;

public class BranchDetailsNotFound extends Exception {
    public BranchDetailsNotFound(){
        super();
    }
    public BranchDetailsNotFound(String msg){
        super(msg);
    }
    public BranchDetailsNotFound(String msg, Throwable throwable){
        super(msg,throwable);
    }
}
