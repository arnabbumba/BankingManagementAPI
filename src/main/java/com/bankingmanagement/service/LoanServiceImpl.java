package com.bankingmanagement.service;

import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.entity.Loan;
import com.bankingmanagement.exception.LoanDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.model.LoanDTO;
import com.bankingmanagement.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LoanServiceImpl implements LoanService{
    @Autowired
    LoanRepository loanRepository;

    @Override
    public List<LoanDTO> findAll() throws LoanDetailsNotFound {
        log.info("Start: LoanServiceImpl.findAll");

        List<Loan> loanList = loanRepository.findAll();
        log.info("Loan Details , loanList:{}",loanList);

        if(CollectionUtils.isEmpty(loanList)){
            log.info("Loan Details Not Found");
            throw new LoanDetailsNotFound("Loan Details Not Found");
        }

        List<LoanDTO> loanDTOList = loanList.stream().map(loan->{

            LoanDTO loanDTO = new LoanDTO();
            loanDTO.setLoanID(loan.getLoanID());
            loanDTO.setLoanType(loan.getLoanType());
            loanDTO.setLoanAmount(loan.getLoanAmount());

            BranchDTO branchDTO = new BranchDTO();
            branchDTO.setBranchId(loan.getBranch().getBranchId());
            branchDTO.setBranchName(loan.getBranch().getBranchName());
            branchDTO.setBranchAddress(loan.getBranch().getBranchAddress());

            BankDTO bankDTO = new BankDTO();
            bankDTO.setBankCode(loan.getBranch().getBank().getBankCode());
            bankDTO.setBankName(loan.getBranch().getBank().getBankName());
            bankDTO.setBankAddress(loan.getBranch().getBank().getBankAddress());
            branchDTO.setBankDTO(bankDTO);
            loanDTO.setBranch(branchDTO);

            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerID(loan.getCustomer().getCustomerID());
            customerDTO.setCustomerName(loan.getCustomer().getCustomerName());
            customerDTO.setCustomerAddress(loan.getCustomer().getCustomerAddress());
            customerDTO.setCustomerPhone(loan.getCustomer().getCustomerPhone());
            loanDTO.setCustomer(customerDTO);

            return loanDTO;
        }).collect(Collectors.toList());

        log.info("End: LoanServiceImpl.findAll");


        return loanDTOList;
    }
}
