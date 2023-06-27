package com.bankingmanagement.controller;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.exception.AccountDetailsNotFound;
import com.bankingmanagement.model.AccountDTO;
import com.bankingmanagement.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        log.info("Inside the AccountController.getAllAccounts");
        List<AccountDTO> accountDTOList = null;
        try {
            accountDTOList = accountService.findAll();
            log.info("Account List:{}", accountDTOList);
        } catch (
                AccountDetailsNotFound ex1){
            log.error("Account Details Not found", ex1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex){
            log.error("Exception while getting the accounts", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of AccountController.getAllAccounts");
        return new ResponseEntity<>(accountDTOList, HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> getAccountByAccountNumber(@PathVariable("accountNumber") int accountNumber){
        log.info("Start: AccountController.getAccountByAccountNumber , accountNumber: {}",accountNumber);
        AccountDTO accountDTO = null;
        if(accountNumber<=0){
            log.info("Invalid Account Number , accountNumber:{}",accountNumber);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            accountDTO = accountService.findAccountByAccountNumber(accountNumber);
            log.info("Get Account details by Account Number , accountDTO:{}",accountDTO);
            if(accountDTO == null){
                log.info("No Data found for Account Number , accountNumber:{}",accountNumber);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            log.error("Exception while getting the accounts", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End: AccountController.getAccountByAccountNumber");

        return new ResponseEntity<>(accountDTO,HttpStatus.OK);
    }


    @GetMapping("/accountType")
    public ResponseEntity<List<AccountDTO>> getAccountByAccountType(@RequestParam("accountType") String accountType){
        log.info("Start: AccountController.getAccountByAccountType , accountType: {}",accountType);
        List<AccountDTO>  accountDTOList = null;
        if(StringUtils.isEmpty(accountType)){
            log.info("Invalid Account Type , accountType:{}",accountType);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            accountDTOList = accountService.findAccountByAccountType(accountType);
            log.info("Get Account details by Account Number , accountDTOList:{}",accountDTOList);
            if(CollectionUtils.isEmpty(accountDTOList)){
                log.info("No Data found for Account Number , accountType:{}",accountType);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            log.error("Exception while getting the accounts", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End: AccountController.getAccountByAccountType");

        return new ResponseEntity<>(accountDTOList,HttpStatus.OK);
    }
}