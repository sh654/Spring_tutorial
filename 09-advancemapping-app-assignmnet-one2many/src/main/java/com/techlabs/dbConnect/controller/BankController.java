package com.techlabs.dbConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbConnect.dto.BankDto;
import com.techlabs.dbConnect.entity.Bank;
import com.techlabs.dbConnect.entity.SalaryAccount;
import com.techlabs.dbConnect.service.BankService;

@RestController
@RequestMapping("/bankapp")
public class BankController {

	@Autowired
	private BankService bankService;
	
	@PostMapping("/bank")
	public ResponseEntity<Bank> addBank(@RequestBody BankDto bankdto){
		return ResponseEntity.ok(bankService.addBank(bankdto));
	}
	
	@PutMapping("/bank")
	public ResponseEntity<Bank> allocateSalaryAccounts(@RequestParam int bankId,@RequestBody List<SalaryAccount> salaryAccounts){
		return ResponseEntity.ok(bankService.updateListOfSalaryAccount(bankId, salaryAccounts));
	}
}
