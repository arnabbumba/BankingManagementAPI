package com.bankingmanagement.service;

import com.bankingmanagement.exception.AccountDetailsNotFound;
import com.bankingmanagement.model.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> findAll() throws AccountDetailsNotFound;

    AccountDTO findAccountByAccountNumber(int accountNumber) throws AccountDetailsNotFound;

    List<AccountDTO> findAccountByAccountType(String accountType) throws AccountDetailsNotFound;
}
