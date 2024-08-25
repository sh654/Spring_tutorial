package com.techlabs.dbConnect.service;

import com.techlabs.dbConnect.dto.SalaryTransactionsDto;
import com.techlabs.dbConnect.entity.SalaryTransactions;

public interface SalaryTransactionService {

	SalaryTransactions addSalaryTransaction(SalaryTransactionsDto salaryTransactionDto);
}
