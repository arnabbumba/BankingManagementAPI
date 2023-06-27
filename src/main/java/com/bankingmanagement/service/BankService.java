package com.bankingmanagement.service;

import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.model.BankRequest;

import java.util.List;

public interface BankService {
    List<BankDTO> findAll() throws BankDetailsNotFound;

    BankDTO saveBank(BankRequest bankRequest) throws BankDetailsNotFound;
}