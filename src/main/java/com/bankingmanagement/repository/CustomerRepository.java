package com.bankingmanagement.repository;

import com.bankingmanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findAllByCustomerName(String customerName);
    @Query(value = "select customer from Customer customer where customerName=:customerName")
    public Optional<Customer> findCustomerByName(@Param("customerName") String customerName);

}