package com.techlabs.dbConnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.techlabs.dbConnect.dtos.AccountsDto;
import com.techlabs.dbConnect.dtos.PageResponse;
import com.techlabs.dbConnect.entity.Accounts;
import com.techlabs.dbConnect.enums.TransactionType;
import com.techlabs.dbConnect.repository.AccountRepository;
import com.techlabs.dbConnect.service.AccountService;

import jakarta.validation.Valid;

@RequestMapping("/bank")
@RestController
@Validated
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@PostMapping("/accounts")
	public ResponseEntity<AccountsDto> addAccount(@Valid @RequestBody Accounts accounts){
		AccountsDto accountDto =  accountService.addAccount(accounts);
		return ResponseEntity.ok(accountDto);
	}
	
	@PutMapping("/accounts")
	public ResponseEntity<AccountsDto> updateAccount(@RequestParam(name="accountId") int accountId,@Valid @RequestBody AccountsDto accountsDto){
		AccountsDto accountDao = accountService.updateAccount(accountId, accountsDto);
		return ResponseEntity.ok(accountDao);
	}
	
	@GetMapping("/accounts")
	public ResponseEntity<PageResponse<AccountsDto>> getAllAccounts(@RequestParam int pageNo, @RequestParam int pageSize){
		PageResponse<AccountsDto> accountsDto = accountService.getAllAccounts(pageNo, pageSize);
		return ResponseEntity.ok(accountsDto);
	}
	
	@GetMapping("/accounts/number")
	public ResponseEntity<AccountsDto> getAccountByAccountNumber(@RequestParam long accountNumber){
		return ResponseEntity.ok(accountService.getAccountByAccountNumber(accountNumber));
	}
	
	@PutMapping("/accounts/debit")
	public ResponseEntity<String> debit(@Valid @RequestParam Long accountNumber,@Valid @RequestParam double amount) {
		accountService.debit(accountNumber, accountNumber, amount, TransactionType.DEBIT);
		return ResponseEntity.ok("Debited Successfully");
	}
	
	@PutMapping("/accounts/credit")
	public ResponseEntity<String> credit(@Valid @RequestParam Long accountNumber,@Valid @RequestParam double amount){
		accountService.credit(accountNumber, accountNumber, amount, TransactionType.CREDIT);
		return ResponseEntity.ok("Credited Successfully");
	}
	
	@PutMapping("/accounts/transfer")
	public ResponseEntity<String> transfer(@Valid @RequestParam Long accountNumber, @Valid @RequestParam Long receiverNumber, @Valid @RequestParam double amount){
		accountService.transfer(accountNumber, receiverNumber, amount);
		return ResponseEntity.ok("Transfer Success");
	}
	
}
