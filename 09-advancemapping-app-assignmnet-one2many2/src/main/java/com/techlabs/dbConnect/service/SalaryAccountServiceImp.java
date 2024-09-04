package com.techlabs.dbConnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dto.SalaryAccountDto;
import com.techlabs.dbConnect.dto.SalaryTransactionsDto;
import com.techlabs.dbConnect.entity.Bank;
import com.techlabs.dbConnect.entity.Employee;
import com.techlabs.dbConnect.entity.SalaryAccount;
import com.techlabs.dbConnect.entity.SalaryTransactions;
import com.techlabs.dbConnect.repository.BankRepository;
import com.techlabs.dbConnect.repository.EmployeeRepo;
import com.techlabs.dbConnect.repository.SalaryAccountRepository;
import com.techlabs.dbConnect.repository.SalaryTransactionRepository;


@Service
public class SalaryAccountServiceImp implements SalaryAccountService{
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private SalaryAccountRepository salaryAccountRepo;
	
	@Autowired
	private SalaryTransactionRepository salaryTransactionRepo;
	
	@Autowired
	private BankRepository bankRepo;
	
	 public SalaryAccount updateSalaryAccount(SalaryAccountDto salaryAccountDto, long accountNumber) {
        // Find the employee by account number
        Optional<Employee> optionalEmployee = employeeRepo.findBySalaryAccountAccountNumber(accountNumber);
        
        if (!optionalEmployee.isPresent()) {
            // Handle case where the employee is not found
            return null;
        }
        
        Employee employee = optionalEmployee.get();
        SalaryAccount salaryAccount = employee.getSalaryAccount();

        if (salaryAccount == null) {
            // Handle case where the salary account is not found
//            throw new Exception("SalaryAccount not found for account number: " + accountNumber);
        	return null;
        }
        
        // Update the account holder's name
        salaryAccount.setAccountHolderName(salaryAccountDto.getAccountHolderName());
        
        // Save the updated salary account
        return salaryAccountRepo.save(salaryAccount);
    }


	@Override
	public SalaryAccount updateBankAccountId(Bank bank, long accountNumber) {
		// TODO Auto-generated method stub
		Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepo.findById(accountNumber);
		if(!optionalSalaryAccount.isPresent()) {
			return null;
		}
		
		SalaryAccount salaryAccount = optionalSalaryAccount.get();
		if(salaryAccount ==null) {
			return null;
		}
		
		Optional<Bank> optionalBank =  bankRepo.findById(bank.getBankId());
		if(!optionalBank.isPresent()) {
			return null;
		}
		
		Bank bankDb = optionalBank.get();
		salaryAccount.setBank(bankDb);
		
	    // Save the updated SalaryAccount
	    return salaryAccountRepo.save(salaryAccount);
	}


	
//	@Override
//	public  SalaryAccount allocateTransactions(List<SalaryTransactions> salaryTransactions, long accountNumber) {
//	    // Find the SalaryAccount by account number
//	    Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepo.findById(accountNumber);
//	    if (!optionalSalaryAccount.isPresent()) {
//	        return null;
//	    }
//	    
//	    SalaryAccount salaryAccountDb = optionalSalaryAccount.get();
//	    
//	    // Associate each SalaryTransaction with the SalaryAccount
//	    for (SalaryTransactions transaction : salaryTransactions) {
//	        transaction.setSalaryAccount(salaryAccountDb);
//	    }
//
//	    // Add the transactions to the SalaryAccount's existing transactions
//	    List<SalaryTransactions> existingTransactions = salaryAccountDb.getSalaryTransactions();
//	    existingTransactions.addAll(salaryTransactions);
//	    
//	    // Save the updated SalaryAccount along with the new transactions
//	    salaryAccountDb.setSalaryTransactions(existingTransactions);
//	    return salaryAccountRepo.save(salaryAccountDb);
//	}
	
	@Override
	public SalaryAccount allocateTransactions(long accountNumber, List<SalaryTransactions> transactions) {
	    // Find the SalaryAccount by account number
	    Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepo.findById(accountNumber);
	    if (!optionalSalaryAccount.isPresent()) {
	        return null; // Handle case where the SalaryAccount is not found
	    }
	    
	    SalaryAccount salaryAccountDb = optionalSalaryAccount.get();
	    
	    // Get the existing transactions for the SalaryAccount
	    List<SalaryTransactions> existingTransactions = salaryAccountDb.getSalaryTransactions();
	    
	    // Iterate over the provided transactions
	    transactions.forEach(transaction -> {
	        // Find each transaction by ID
	        Optional<SalaryTransactions> transactionOptional = salaryTransactionRepo.findById(transaction.getTransactionId());
	        if (transactionOptional.isPresent()) {
	            SalaryTransactions temp = transactionOptional.get();
	            // Associate each transaction with the SalaryAccount
	            temp.setSalaryAccount(salaryAccountDb);
	            // Add the transaction to the list of existing transactions
	            existingTransactions.add(temp);
	        }
	    });
	    
	    // Set the updated list of transactions to the SalaryAccount
	    salaryAccountDb.setSalaryTransactions(existingTransactions);
	    
	    // Save the updated SalaryAccount
	    return salaryAccountRepo.save(salaryAccountDb);
	}




	
}
