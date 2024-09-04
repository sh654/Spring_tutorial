package com.techlabs.dbConnect.service;

import java.util.List;

import com.techlabs.dbConnect.dto.SalaryAccountDto;
import com.techlabs.dbConnect.entity.Bank;
import com.techlabs.dbConnect.entity.SalaryAccount;
import com.techlabs.dbConnect.entity.SalaryTransactions;

public interface SalaryAccountService {

	SalaryAccount updateSalaryAccount(SalaryAccountDto salaryAccountDto, long accountNumber);

	SalaryAccount updateBankAccountId(Bank bank, long accountNumber);
	
//	SalaryAccount allocateTransactions(List<SalaryTransactions> salaryTransactions, long accountNumber);
	SalaryAccount allocateTransactions(long accountNumber, List<SalaryTransactions> transactions);
		// TODO Auto-generated method stub
		
}
