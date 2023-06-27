package com.bankingmanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "Account_TBL")
public class Account {

    @Id
    @Column(name = "Account_Number")
    private  int accountNo;

    @Column(name = "Account_Type")
    private String accountType;

    @Column(name = "Account_Balance")
    private double balance;

    @ManyToOne
    @JoinColumn(name = "Branch_ID")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "Cust_ID")
    private Customer customer;

}
