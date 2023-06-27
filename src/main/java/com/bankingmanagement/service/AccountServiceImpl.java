package com.bankingmanagement.service;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.exception.AccountDetailsNotFound;
import com.bankingmanagement.model.AccountDTO;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepository accountRepository;
    @Override
    public List<AccountDTO> findAll() throws AccountDetailsNotFound {
        log.info("Inside the AccountServiceImpl.findAll");

        List<Account> accountList= accountRepository.findAll();
        log.info("List of Accounts, accountList:{}",accountList);

        if(CollectionUtils.isEmpty(accountList)){
            log.info("No Account Found");
            throw new AccountDetailsNotFound("No Account Found");
        }

        List<AccountDTO> accountDTOList = accountList.stream().map(account -> {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAccountNo(account.getAccountNo());
            accountDTO.setAccountType(account.getAccountType());
            accountDTO.setBalance(account.getBalance());

            BranchDTO branchDTO = new BranchDTO();
            branchDTO.setBranchAddress(account.getBranch().getBranchAddress());
            branchDTO.setBranchName(account.getBranch().getBranchName());
            branchDTO.setBranchId(account.getBranch().getBranchId());

            accountDTO.setBranch(branchDTO);

            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerID(account.getCustomer().getCustomerID());
            customerDTO.setCustomerName(account.getCustomer().getCustomerName());
            customerDTO.setCustomerAddress(account.getCustomer().getCustomerAddress());
            customerDTO.setCustomerPhone(account.getCustomer().getCustomerPhone());
            accountDTO.setCustomer(customerDTO);

            return accountDTO;
        }).collect(Collectors.toList());

        log.info("End of AccountServiceImpl.findBankDetails");
        return accountDTOList;
    }

    @Override
    public AccountDTO findAccountByAccountNumber(int accountNumber) throws AccountDetailsNotFound {

        log.info("Start:  AccountServiceImpl.findAccountByAccountNumber , accountNumber:{}",accountNumber);

        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);
        if(!optionalAccount.isPresent()){
            log.info("No Data found for accountNumber:{}",accountNumber);
            throw new AccountDetailsNotFound("No Data found ");
        }
        log.info("Account details for accountNumber :{} and response :{}",accountNumber,(Account)optionalAccount.get());

        AccountDTO accountDTO = new AccountDTO();
        Account account = optionalAccount.get();

        accountDTO.setAccountNo(account.getAccountNo());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setBalance(account.getBalance());

        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setBranchAddress(account.getBranch().getBranchAddress());
        branchDTO.setBranchName(account.getBranch().getBranchName());
        branchDTO.setBranchId(account.getBranch().getBranchId());

        BankDTO bankDTO = new BankDTO();
        bankDTO.setBankCode(account.getBranch().getBank().getBankCode());
        bankDTO.setBankName(account.getBranch().getBank().getBankName());
        bankDTO.setBankAddress(account.getBranch().getBank().getBankAddress());

        branchDTO.setBankDTO(bankDTO);
        accountDTO.setBranch(branchDTO);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerID(account.getCustomer().getCustomerID());
        customerDTO.setCustomerName(account.getCustomer().getCustomerName());
        customerDTO.setCustomerAddress(account.getCustomer().getCustomerAddress());
        customerDTO.setCustomerPhone(account.getCustomer().getCustomerPhone());
        accountDTO.setCustomer(customerDTO);


        log.info("End:  AccountServiceImpl.findAccountByAccountNumber");
        return accountDTO;
    }

    @Override
    public List<AccountDTO> findAccountByAccountType(String accountType) throws AccountDetailsNotFound {
        log.info("Inside the AccountServiceImpl.findAccountByAccountType");

        List<Account> accountList= accountRepository.findAllByAccountType(accountType);
        log.info("List of Accounts, accountList:{}",accountList);

        if(CollectionUtils.isEmpty(accountList)){
            log.info("No Account Found");
            throw new AccountDetailsNotFound("No Account Found");
        }

        List<AccountDTO> accountDTOList = accountList.stream().map(account -> {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAccountNo(account.getAccountNo());
            accountDTO.setAccountType(account.getAccountType());
            accountDTO.setBalance(account.getBalance());

            BranchDTO branchDTO = new BranchDTO();
            branchDTO.setBranchAddress(account.getBranch().getBranchAddress());
            branchDTO.setBranchName(account.getBranch().getBranchName());
            branchDTO.setBranchId(account.getBranch().getBranchId());

            accountDTO.setBranch(branchDTO);

            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerID(account.getCustomer().getCustomerID());
            customerDTO.setCustomerName(account.getCustomer().getCustomerName());
            customerDTO.setCustomerAddress(account.getCustomer().getCustomerAddress());
            customerDTO.setCustomerPhone(account.getCustomer().getCustomerPhone());
            accountDTO.setCustomer(customerDTO);

            return accountDTO;
        }).collect(Collectors.toList());

        log.info("End of AccountServiceImpl.findAccountByAccountType");
        return accountDTOList;
    }
}
