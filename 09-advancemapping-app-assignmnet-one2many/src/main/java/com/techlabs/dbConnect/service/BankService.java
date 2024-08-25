package com.techlabs.dbConnect.service;


import java.util.List;

import com.techlabs.dbConnect.dto.BankDto;
import com.techlabs.dbConnect.entity.Bank;
import com.techlabs.dbConnect.entity.SalaryAccount;

public interface BankService {

	Bank addBank(BankDto bankdto);
	
	Bank updateListOfSalaryAccount(int bankId, List<SalaryAccount> salaryAccounts);
}
