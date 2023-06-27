package com.bankingmanagement.service;

import com.bankingmanagement.entity.Bank;
import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.exception.CustomerDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.model.BankRequest;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.repository.BankRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BankServiceImpl implements BankService{
    @Autowired
    BankRepository bankRepository;
    @Override
    public List<BankDTO> findAll() throws BankDetailsNotFound {
        log.info("Inside the BankServiceImpl.findAll");

        List<Bank> bankList = bankRepository.findAll();
        log.info("List of banks , bankList:{}",bankList);

        if(CollectionUtils.isEmpty(bankList)){
            log.info("No Bank details found");
            throw new BankDetailsNotFound("No Bank details found");
        }

        List<BankDTO> bankDTOList = bankList.stream().map(bank -> {
            BankDTO bankDTO = new BankDTO();
            bankDTO.setBankAddress(bank.getBankAddress());
            bankDTO.setBankName(bank.getBankName());
            bankDTO.setBankCode(bank.getBankCode());
            bankDTO.setBranchDTOList(bank.getBranches().stream().map(branch -> {
                BranchDTO branchDTO = new BranchDTO();
                branchDTO.setBranchId(branch.getBranchId());
                branchDTO.setBranchName(branch.getBranchName());
                branchDTO.setBranchAddress(branch.getBranchAddress());

                return branchDTO;
            }).collect(Collectors.toList())
            );
            return bankDTO;
        }).collect(Collectors.toList());


        log.info("End of BankServiceImpl.findAll");

        return bankDTOList;
    }

    @Override
    public BankDTO saveBank(BankRequest bankRequest) throws BankDetailsNotFound {
        log.info("Start: BankServiceImpl.saveBank with bankRequest:{}",bankRequest);

        if(bankRequest == null){
            log.info("Invalid bank request,bankRequest:{} ", bankRequest);
            throw new IllegalArgumentException("Invalid bankRequest");
        }

        Bank bank = new Bank();
        bank.setBankName(bankRequest.getBankName());
        bank.setBankAddress(bankRequest.getBankAddress());
        Bank bankReturn = bankRepository.save(bank);

        if(bankReturn == null){
            log.info("Exception while saving the bank");
            throw new BankDetailsNotFound("Exception while saving the bank");
        }

        BankDTO bankDTO = new BankDTO();
        bankDTO.setBankCode(bankReturn.getBankCode());
        bankDTO.setBankName(bankReturn.getBankName());
        bankDTO.setBankAddress(bankReturn.getBankAddress());

        log.info("End: BankServiceImpl.saveBank");
        return bankDTO;
    }
}
