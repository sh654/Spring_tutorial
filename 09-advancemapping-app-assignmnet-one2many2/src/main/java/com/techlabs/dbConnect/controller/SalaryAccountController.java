package com.techlabs.dbConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbConnect.dto.SalaryAccountDto;
import com.techlabs.dbConnect.entity.Bank;
import com.techlabs.dbConnect.entity.SalaryAccount;
import com.techlabs.dbConnect.service.SalaryAccountService;

@RestController
@RequestMapping("/bankapp")
public class SalaryAccountController {

	@Autowired
	private SalaryAccountService salaryAccountService;
	
	@PutMapping("/salaryaccount")
    public ResponseEntity<SalaryAccount> updateSalaryAccount(@RequestParam long accountNumber, @RequestBody SalaryAccountDto salaryAccountDto) {
        SalaryAccount updatedSalaryAccount = salaryAccountService.updateSalaryAccount(salaryAccountDto, accountNumber);
        return ResponseEntity.ok(updatedSalaryAccount);
    }
	
	@PutMapping("/salaryAccount/UpdateBank")
	public ResponseEntity<SalaryAccount> updateBank(@RequestParam long accountNumber,@RequestBody Bank bank){
		return ResponseEntity.ok(salaryAccountService.updateBankAccountId(bank, accountNumber));
	}
}
