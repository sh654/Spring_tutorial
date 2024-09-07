package com.techlabs.dbConnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbConnect.service.AdminService;

@RestController
@RequestMapping("/bank")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/verify-document/{documentId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> verifyDocument(@PathVariable int documentId, @RequestParam int customerId){
		String result = adminService.verifyCustomerDocument(documentId, customerId);
		return ResponseEntity.ok(result);
	}
	
}
