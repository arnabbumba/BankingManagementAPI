package com.bankingmanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Bank_TBL")
public class Bank implements Serializable {

    @Id
    @Column(name = "Bank_Code")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BANK_ID_SEQUENCE")
    @SequenceGenerator(name = "BANK_ID_SEQUENCE",sequenceName = "BANK_ID_SEQUENCE",allocationSize = 1)
    private int bankCode;

    @Column(name = "Bank_Name")
    private String bankName;

    @Column(name = "Bank_Address")
    private String bankAddress;

    @OneToMany(mappedBy = "bank")
    private List<Branch> branches;
}
