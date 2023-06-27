package com.bankingmanagement.service;

import com.bankingmanagement.exception.BranchDetailsNotFound;
import com.bankingmanagement.model.BranchDTO;

import java.util.List;

public interface BranchService {
    List<BranchDTO> findAll() throws BranchDetailsNotFound;

}
