package com.techlabs.dbConnect.controllers;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbConnect.dtos.PageResponse;
import com.techlabs.dbConnect.dtos.TransactionDto;
import com.techlabs.dbConnect.enums.TransactionType;
import com.techlabs.dbConnect.service.TransactionService;

@RestController
@RequestMapping("/bank")
public class TransactionController {
	

	 @Autowired
	 private TransactionService transactionService;

	 @GetMapping("/transactions/")
	 @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
	 public ResponseEntity<PageResponse<TransactionDto>> getAccountTransaction(@RequestParam int pageNo,@RequestParam int pageSize,@RequestParam Long accountNumber){
		 return ResponseEntity.ok(transactionService.getAccountTransaction(pageNo, pageSize, accountNumber));
	 }
	
	 @GetMapping("/transactions")
	 @PreAuthorize("hasRole('ADMIN')")
	 public ResponseEntity<PageResponse<TransactionDto>> getAllTransaction(@RequestParam int pageNo,@RequestParam int pageSize){
		 return ResponseEntity.ok( transactionService.getAllTransactions(pageNo, pageSize));
	 }
	 
	 @GetMapping("/transaction")
	 @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
	 public ResponseEntity<PageResponse<TransactionDto>> getByAccountNumber(@RequestParam int pageNo,@RequestParam int pageSize, @RequestParam Long accountNumber){
		 return ResponseEntity.ok(transactionService.getSenderTransaction(pageNo, pageSize, accountNumber));
	 }
	 
	 @GetMapping("/export-transaction")
	 @PreAuthorize("hasRole('CUSTOMER')")
	 public ResponseEntity<InputStreamResource> exportTransactions(
			 @RequestParam long accountNumber,
			 @RequestParam TransactionType type){
		 ByteArrayInputStream pdfStream = transactionService.exportTransactionToPdf(accountNumber, type);
		 
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Content-Disposition", "inline; filename=transaction.pdf");
		 return ResponseEntity.ok()
				 .headers(headers)
				 .contentType(MediaType.APPLICATION_PDF)
				 .body(new InputStreamResource(pdfStream));
	 }
}
