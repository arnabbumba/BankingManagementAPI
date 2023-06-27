package com.bankingmanagement.controller;

import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.service.BankService;
import com.bankingmanagement.service.BranchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BranchControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    @Test
    public void testGetBranches() throws Exception {
        List<BranchDTO> branchDTOList = new ArrayList<>();
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setBranchName("Chinarpark");
        branchDTO.setBranchAddress("Kalipark");
        BankDTO bankDTO = new BankDTO();
        bankDTO.setBankName("SBI");
        bankDTO.setBankAddress("Bangalore");
        branchDTO.setBankDTO(bankDTO);
        branchDTOList.add(branchDTO);
        when(branchService.findAll()).thenReturn(branchDTOList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/branches")
                .contentType(MediaType.APPLICATION_JSON);


        // compare the body
        mockMvc.perform(requestBuilder).andExpect(jsonPath("$", Matchers.hasSize(1))).andDo(print());

        // compare the status code
        mockMvc.perform(requestBuilder).andExpect(status().isOk());

    }

}