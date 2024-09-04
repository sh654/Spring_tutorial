package com.techlabs.dbConnect.dto;

import java.time.LocalDate;

import com.techlabs.dbConnect.entity.Salary;
import com.techlabs.dbConnect.entity.SalaryAccount;
import com.techlabs.dbConnect.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SalaryTransactionsDto {

	private int transactionId;
	
	private LocalDate transactionDate;
	
	private double amount;
	
	private Status status;
	
	private Salary salaryTransaction;
	
    private SalaryAccount salaryAccount;
	
}
