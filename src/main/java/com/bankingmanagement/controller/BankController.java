package com.bankingmanagement.controller;

import com.bankingmanagement.exception.AccountDetailsNotFound;
import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.model.BankRequest;
import com.bankingmanagement.service.AccountService;
import com.bankingmanagement.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/banks")
public class BankController {
    @Autowired
    BankService bankService;
    @GetMapping
    public ResponseEntity<List<BankDTO>> getAllBanks() {
        log.info("Inside the BankController.getAllBanks");
        List<BankDTO> bankDTOList = null;
        try {
            bankDTOList = bankService.findAll();
            log.info("Bank List:{}", bankDTOList);
        } catch (
                BankDetailsNotFound ex1){
            log.error("Bank Details Not found", ex1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex){
            log.error("Exception while getting the accounts", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of BankController.getAllBanks");
        return new ResponseEntity<>(bankDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BankDTO> saveBank(@RequestBody BankRequest bankRequest){
        log.info("Start: BankController.saveBank with bankRequest:{}",bankRequest);
        if(bankRequest == null){
            log.info("Invalid Bank Request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BankDTO bankDTO = null;
        try{
            bankDTO = bankService.saveBank(bankRequest);
            log.info("Bank details, bankDTO:{}", bankDTO);

            if(bankDTO == null) {
                log.info("Bank details not found for the bankDTO:{}", bankDTO);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex){
            log.error("Exception while getting saving customer details", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End: BankController.saveBank");
        return new ResponseEntity<>(bankDTO,HttpStatus.OK);
    }
}