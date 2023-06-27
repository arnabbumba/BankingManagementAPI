package com.bankingmanagement.service;

import com.bankingmanagement.exception.LoanDetailsNotFound;
import com.bankingmanagement.model.LoanDTO;

import java.util.List;

public interface LoanService {

    List<LoanDTO> findAll() throws LoanDetailsNotFound;
}
