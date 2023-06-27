package com.bankingmanagement.service;

import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.exception.BranchDetailsNotFound;
import com.bankingmanagement.model.AccountDTO;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.repository.BranchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BranchServiceImpl implements BranchService{
    @Autowired
    BranchRepository branchRepository;
    @Override
    public List<BranchDTO> findAll() throws BranchDetailsNotFound {
        log.info("Inside the BranchServiceImpl.findAll");

        List<Branch> branchList= branchRepository.findAll();
        log.info("List of Branches, branchList:{}",branchList);

        if(CollectionUtils.isEmpty(branchList)){
            log.info("No Branch Found");
            throw new BranchDetailsNotFound("No Branch Found");
        }

        List<BranchDTO> branchDTOList = branchList.stream().map(branch -> {

            BranchDTO branchDTO = new BranchDTO();

            branchDTO.setBranchId(branch.getBranchId());
            branchDTO.setBranchName(branch.getBranchName());
            branchDTO.setBranchAddress(branch.getBranchAddress());

            BankDTO bankDTO = new BankDTO();
            bankDTO.setBankCode(branch.getBank().getBankCode());
            bankDTO.setBankName(branch.getBank().getBankName());
            bankDTO.setBankAddress(branch.getBank().getBankAddress());
            branchDTO.setBankDTO(bankDTO);

            return branchDTO;
        }).collect(Collectors.toList());


        log.info("End of BranchServiceImpl.findAll");
        return branchDTOList;
    }
}
