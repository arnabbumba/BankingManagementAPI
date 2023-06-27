package com.bankingmanagement.model;

import lombok.Data;

@Data

public class BranchDTO {
    private String branchName;
    private String branchAddress;
    private int branchId;
    private BankDTO bankDTO;

}