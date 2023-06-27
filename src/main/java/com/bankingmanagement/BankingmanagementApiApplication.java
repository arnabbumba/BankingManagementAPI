package com.bankingmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class BankingmanagementApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(BankingmanagementApiApplication.class, args);
		log.info("Banking Management application has been started");
	}

}
