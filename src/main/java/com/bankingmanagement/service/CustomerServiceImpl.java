package com.bankingmanagement.service;

import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.exception.CustomerDetailsNotFound;
import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.model.CustomerRequest;
import com.bankingmanagement.model.CustomerRequestforUpdate;
import com.bankingmanagement.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public List<CustomerDTO> findAll() throws CustomerDetailsNotFound {
        log.info("Start: CustomerServiceImpl.findAll");

        List<Customer> customerList = customerRepository.findAll();
        log.info("customer list returned , customerList:{}",customerList);

        if(CollectionUtils.isEmpty(customerList)){
            log.info("No Customer Found");
            throw new CustomerDetailsNotFound("No Customer found");
        }

        List<CustomerDTO> customerDTOList = customerList.stream().map(customer -> {

            CustomerDTO customerDTO = new CustomerDTO();

            customerDTO.setCustomerID(customer.getCustomerID());
            customerDTO.setCustomerName(customer.getCustomerName());
            customerDTO.setCustomerAddress(customer.getCustomerAddress());
            customerDTO.setCustomerPhone(customer.getCustomerPhone());

            return customerDTO;
        }).collect(Collectors.toList());

        log.info("End: CustomerServiceImpl.findAll");
        return customerDTOList;
    }

    @Override
    public CustomerDTO findCustomerByName(String customerName) throws CustomerDetailsNotFound {
        log.info("Start: CustomerServiceImpl.findCustomerByName with customerName:{}",customerName);

        /*Optional<List<Customer>> customerListOptional = customerRepository.findCustomerByName(customerName);
        log.info("customer list returned , customerList:{}",customerListOptional);*/
        Optional<Customer> customerListOptional = customerRepository.findCustomerByName(customerName);
        log.info("customer list returned , customerList:{}",customerListOptional);

        if(!customerListOptional.isPresent()){
            log.info("No Customer Found");
            throw new CustomerDetailsNotFound("No Customer found");
        }

        Customer customer = customerListOptional.get();

            CustomerDTO customerDTO = new CustomerDTO();

            customerDTO.setCustomerID(customer.getCustomerID());
            customerDTO.setCustomerName(customer.getCustomerName());
            customerDTO.setCustomerAddress(customer.getCustomerAddress());
            customerDTO.setCustomerPhone(customer.getCustomerPhone());
        log.info("End: CustomerServiceImpl.findCustomerByName");
            return customerDTO;


    }

    @Override
    public CustomerDTO saveCustomer(CustomerRequest customerRequest) throws CustomerDetailsNotFound {

        log.info("Input to CustomerServiceImpl.saveCustomer, customerRequest:{}", customerRequest);

        if(customerRequest == null){
            log.info("Invalid customer request,customerRequest:{} ", customerRequest);
            throw new IllegalArgumentException("Invalid customer request,customerRequest");
        }
        Customer customer = new Customer();
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setCustomerPhone(customerRequest.getCustomerPhone());
        customer.setCustomerAddress(customerRequest.getCustomerAddress());

        Customer customerReturn = customerRepository.save(customer);

        if(customerReturn == null){
            log.info("Exception while saving the customer");
            throw new CustomerDetailsNotFound("Exception while saving the customer");
        }

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerID(customerReturn.getCustomerID());
        customerDTO.setCustomerName(customerReturn.getCustomerName());
        customerDTO.setCustomerPhone(customerReturn.getCustomerPhone());
        customerDTO.setCustomerAddress(customerReturn.getCustomerAddress());
        log.info("Exit from CustomerServiceImpl.saveCustomer, customerDTO:{}", customerDTO);
        return customerDTO;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerRequestforUpdate customerRequest) throws CustomerDetailsNotFound {
        log.info("Input to CustomerServiceImpl.updateCustomer, customerRequest:{}", customerRequest);

        if(customerRequest == null){
            log.info("Invalid customer request,customerRequest:{} ", customerRequest);
            throw new IllegalArgumentException("Invalid customer request,customerRequest");
        }
        Customer customer = new Customer();
        customer.setCustomerID(customerRequest.getCustomerId());
        if(customerRequest.getCustomerName()!=null)
        customer.setCustomerName(customerRequest.getCustomerName());
        if(customerRequest.getCustomerPhone()!=null)
        customer.setCustomerPhone(customerRequest.getCustomerPhone());
        if(customerRequest.getCustomerAddress()!=null)
        customer.setCustomerAddress(customerRequest.getCustomerAddress());

        Customer customerReturn = customerRepository.save(customer);

        if(customerReturn == null){
            log.info("Exception while saving the customer");
            throw new CustomerDetailsNotFound("Exception while saving the customer");
        }

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerID(customerReturn.getCustomerID());
        customer.setCustomerName(customerReturn.getCustomerName());
        customer.setCustomerPhone(customerReturn.getCustomerPhone());
        customer.setCustomerAddress(customerReturn.getCustomerAddress());
        log.info("Exit from CustomerServiceImpl.updateCustomer, customerDTO:{}", customerDTO);
        return customerDTO;
    }

    @Override
    public void delete(int customerID) {
        log.info("Input to CustomerServiceImpl.delete, customerID:{}", customerID);

        if(customerID < 0){
            log.info("Invalid customer request,customerID:{} ", customerID);
            throw new IllegalArgumentException("Invalid customer request,customerID");
        }
        customerRepository.deleteById(customerID);
    }

}
