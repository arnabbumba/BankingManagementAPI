package com.bankingmanagement.service;

import com.bankingmanagement.exception.CustomerDetailsNotFound;
import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.model.CustomerRequest;
import com.bankingmanagement.model.CustomerRequestforUpdate;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> findAll() throws CustomerDetailsNotFound;

    CustomerDTO saveCustomer(CustomerRequest customerRequest) throws CustomerDetailsNotFound;

    CustomerDTO updateCustomer(CustomerRequestforUpdate customerRequest) throws CustomerDetailsNotFound;

    void delete(int customerID);

    CustomerDTO findCustomerByName(String customerName) throws CustomerDetailsNotFound;
}
