package com.bankingmanagement.model;

import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.entity.Customer;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
public class AccountDTO {


    private  int accountNo;
    private String accountType;
    private double balance;
    private BranchDTO branch;
    private CustomerDTO customer;

}
