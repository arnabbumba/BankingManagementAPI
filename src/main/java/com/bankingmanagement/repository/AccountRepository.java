package com.bankingmanagement.repository;

import com.bankingmanagement.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Integer> {

    List<Account> findAllByAccountType(String accountType);
}
