package com.bankingmanagement.model;

import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.entity.Customer;
import lombok.Data;

import javax.persistence.*;
@Data
public class LoanDTO {

    private  int loanID;
    private  String loanType;
    private  int loanAmount;
    private BranchDTO branch;
    private CustomerDTO customer;

}
