package com.bankingmanagement.controller;

import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.exception.LoanDetailsNotFound;
import com.bankingmanagement.model.LoanDTO;
import com.bankingmanagement.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    @Autowired
    LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanDTO>> findAllLoans(){
        log.info("Start: LoanController.findAllLoans");
        List<LoanDTO> loanDTOList = null;

        try{
            loanDTOList = loanService.findAll();
            log.info("Loan List:{}", loanDTOList);
        } catch (
                LoanDetailsNotFound ex1){
            log.error("Loan Details Not found", ex1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex){
            log.error("Exception while getting the accounts", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


        log.info("End: LoanController.findAllLoans");
        return new ResponseEntity<>(loanDTOList, HttpStatus.OK);
    }
}
