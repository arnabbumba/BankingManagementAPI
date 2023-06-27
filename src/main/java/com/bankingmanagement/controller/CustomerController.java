package com.bankingmanagement.controller;

import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.exception.CustomerDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.model.CustomerRequest;
import com.bankingmanagement.model.CustomerRequestforUpdate;
import com.bankingmanagement.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        log.info("Inside the CustomerController.getAllCustomers");
        List<CustomerDTO> customerDTOList = null;

        try {
            customerDTOList = customerService.findAll();
            log.info("Customer List:{}", customerDTOList);
            
        } catch (
                CustomerDetailsNotFound ex1) {
            log.error("Customer Details Not found", ex1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("Exception while getting the accounts", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of CustomerController.getAllCustomers");
        return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }

    @GetMapping("/byCustomerName")
    public ResponseEntity<CustomerDTO> getCustomerByCustomerName(@RequestParam("customerName") String customerName) {
        log.info("Inside CustomerController.getCustomerByCustomerName, customerName:{}", customerName);
        String response = null;
        CustomerDTO customerDTOList = null;

        if(StringUtils.isEmpty(customerName)){
            log.info("Invalid Customer Name");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            customerDTOList = customerService.findCustomerByName(customerName);
            log.info("Customer List:{}", customerDTOList);

        }catch (
                CustomerDetailsNotFound ex1) {
            log.error("Customer Details Not found", ex1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("Exception while getting the accounts", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
        return new ResponseEntity<>(customerDTOList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerRequest customerRequest) {
        log.info("Start CustomerController.saveCustomer");
        if (customerRequest == null) {
            log.info("Invalid Customer request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CustomerDTO customerDTO = null;
        try {
            customerDTO = customerService.saveCustomer(customerRequest);
            log.info("Customer details, customerDTO:{}", customerDTO);

            if (customerDTO == null) {
                log.info("Customer details not found for the customerDTO:{}", customerDTO);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.error("Exception while getting saving customer details", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End CustomerController.saveCustomer");
        return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerRequestforUpdate customerRequest) {
        log.info("Start CustomerController.updateCustomer");
        if (customerRequest == null) {
            log.info("Invalid Customer request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CustomerDTO customerDTO = null;
        try {
            customerDTO = customerService.updateCustomer(customerRequest);
            log.info("Customer details, customerDTO:{}", customerDTO);

            if (customerDTO == null) {
                log.info("Customer details not found for the customerDTO:{}", customerDTO);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.error("Exception while getting saving customer details", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End CustomerController.updateCustomer");
        return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("customerID") int customerID){
        log.info("Inside CustomerController.delete, customerID:{}", customerID);
        String response = null;
        if(customerID <=0){
            log.info("Invalid Customer Id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            customerService.delete(customerID);

        }catch (Exception ex){
            log.error("Exception while deleting the Customer details", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>(HttpStatus.OK);
    }
}