package com.techlabs.dbConnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dto.BankDto;
import com.techlabs.dbConnect.entity.Bank;
import com.techlabs.dbConnect.entity.SalaryAccount;
import com.techlabs.dbConnect.repository.BankRepository;
import com.techlabs.dbConnect.repository.SalaryAccountRepository;

@Service
public class BankServiceImp implements BankService{
	
	@Autowired
	private BankRepository bankRepo;
	
	@Autowired
	private SalaryAccountRepository salaryAccountRepo;

	@Override
	public Bank addBank(BankDto bankdto) {
		// TODO Auto-generated method stub
		Bank bank = new Bank();
		bank.setBankId(bankdto.getBankId());
		bank.setBankName(bankdto.getBankName());
		bank.setIfscNumber(bankdto.getIfscNumber());
		return bankRepo.save(bank);
	}

	@Override
	public Bank updateListOfSalaryAccount(int bankId, List<SalaryAccount> salaryAccounts) {
		// TODO Auto-generated method stub
		Bank bankDb;
		Optional<Bank> optionalBank = bankRepo.findById(bankId);
		if(!optionalBank.isPresent()) {
			return null;
		}
		
		bankDb = optionalBank.get();
		List<SalaryAccount> salaryAccountDb = bankDb.getSalaryAccount();
		
		salaryAccounts.forEach((salaryAccount) ->{
			SalaryAccount temp = salaryAccountRepo.findById(salaryAccount.getAccountNumber()).get();
			temp.setBank(bankDb);
			salaryAccountDb.add(temp);
		});
		
		bankDb.setSalaryAccount(salaryAccountDb);
		
		return bankRepo.save(bankDb);
	}

	
	
}
