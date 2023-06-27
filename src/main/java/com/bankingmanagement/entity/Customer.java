package com.bankingmanagement.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Data
@Table(name="Customer_TBL")
public class Customer {

    @Id
    @Column(name = "Cust_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_ID_SEQUENCE")
    @SequenceGenerator(name = "CUST_ID_SEQUENCE",sequenceName = "CUSTOMER_ID_SEQUENCE",allocationSize = 1)
    private int customerID;

    @Column(name = "Cust_Name")
    private String customerName;

    @Column(name = "Cust_Address")
    private String customerAddress;

    @Column(name = "Cust_Phone")
    private String customerPhone;

}