package com.bankingmanagement.controller;

import com.bankingmanagement.exception.AccountDetailsNotFound;
import com.bankingmanagement.exception.BranchDetailsNotFound;
import com.bankingmanagement.model.AccountDTO;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.service.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {
    @Autowired
    BranchService branchService;
    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranches() {
        log.info("Inside the BranchController.getAllBranches");
        List<BranchDTO> branchDTOList = null;
        try {
            branchDTOList = branchService.findAll();
            log.info("Branch List:{}", branchDTOList);
        } catch (
                BranchDetailsNotFound ex1){
            log.error("Branch Details Not found", ex1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex){
            log.error("Exception while getting the accounts", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of BranchController.getAllBranches");
        return new ResponseEntity<>(branchDTOList, HttpStatus.OK);
    }

}