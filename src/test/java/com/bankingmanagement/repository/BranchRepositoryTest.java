package com.bankingmanagement.repository;


import com.bankingmanagement.entity.Branch;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class BranchRepositoryTest {

    @Autowired
    BranchRepository branchRepository;

    @Test
    public void testFindByBranchName(){
        saveBranch();
        Optional<Branch>  branchOptional = branchRepository.findByBranchName("Rajarhat");
        assertEquals("Rajarhat", branchOptional.get().getBranchName());
    }

    public void saveBranch(){
        Branch branch = new Branch();
        branch.setBranchName("Rajarhat");
        branch.setBranchAddress("Kolkata");
        branchRepository.save(branch);

    }
}