package com.techlabs.dbConnect.dto;

import java.util.List;

import com.techlabs.dbConnect.entity.SalaryAccount;
import com.techlabs.dbConnect.enums.BankName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class BankDto {

	private int bankId;
	
	private BankName bankName;
	
	private String ifscNumber;
	
	List<SalaryAccount> salaryAccount;
	
}
