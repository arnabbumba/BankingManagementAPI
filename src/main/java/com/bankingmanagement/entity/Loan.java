package com.bankingmanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Loan_TBL")
public class Loan {

    @Id
    @Column(name = "Loan_ID")
    private  int loanID;

    @Column(name = "Loan_Type")
    private  String loanType;

    @Column(name = "Loan_Amount")
    private  int loanAmount;

    @ManyToOne
    @JoinColumn(name = "Branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "Cust_ID")
    private Customer customer;

}
