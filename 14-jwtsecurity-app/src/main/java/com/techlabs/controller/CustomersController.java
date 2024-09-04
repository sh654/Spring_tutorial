package com.techlabs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.entity.Customers;
import com.techlabs.service.CustomersService;

@RestController
@RequestMapping("/app")
public class CustomersController {

	@Autowired
	private CustomersService customerService;
	
	@GetMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Customers>> getAllCustomers(){
		return ResponseEntity.ok(customerService.getAllCustomers());
	}
	
	@GetMapping("/customers/{customerId}")
	@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
	public ResponseEntity<Customers> getCustomerById(@PathVariable int customerId){
		return ResponseEntity.ok(customerService.getCustomerById(customerId));
	}
}
