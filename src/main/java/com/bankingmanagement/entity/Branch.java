package com.bankingmanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "Branch_TBL")
public class Branch implements Serializable {
    public static final long serialVersionUID= 1878678757l;

    @Id
    @Column(name = "Branch_ID")
    private  int branchId;

    @Column(name = "Branch_Name")
    private String branchName;

    @Column(name = "Branch_Address")
    private String branchAddress;

    @OneToOne
    @JoinColumn(name = "Bank_Code")
    private Bank bank;
}