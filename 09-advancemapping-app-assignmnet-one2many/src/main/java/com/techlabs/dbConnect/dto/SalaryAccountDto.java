package com.techlabs.dbConnect.dto;

import com.techlabs.dbConnect.entity.Bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SalaryAccountDto {

	private long accountNumber;
	
	private String accountHolderName;
	
	private Bank bank;
}
