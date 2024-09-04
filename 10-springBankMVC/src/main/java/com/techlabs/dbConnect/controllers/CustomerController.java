package com.techlabs.dbConnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.techlabs.dbConnect.dtos.CustomersDto;
import com.techlabs.dbConnect.dtos.PageResponse;
import com.techlabs.dbConnect.entity.Customers;
import com.techlabs.dbConnect.service.CustomerService;

import jakarta.validation.Valid;


@RequestMapping("/bank")
@RestController
@Validated
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CustomersDto> addCustomers(@Valid @RequestBody Customers customers){
		CustomersDto customerdto = customerService.addCustomer(customers);
		return ResponseEntity.ok(customerdto);
	}
	
	@GetMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CustomersDto> getCustomerById(@RequestParam(name="customerId") int customerId){
		CustomersDto customersDto = customerService.getCustomerById(customerId);
		return ResponseEntity.ok(customersDto);
	}
	
	@GetMapping("/customers/getall")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PageResponse<CustomersDto>> getAllCustomer(@RequestParam(name="pageNo") int pageNo,@RequestParam(name="pageSize") int pageSize){
		
		return ResponseEntity.ok(customerService.getAllCustomer(pageNo, pageSize));
	}
	
	@DeleteMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCustomer(@RequestParam int customerId){
		customerService.deleteCustomer(customerId);
		return ResponseEntity.ok("Deleted");
	}
	
	@PutMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CustomersDto> updateCustomer(@RequestParam(name="customerId") int customerId,@Valid @RequestBody Customers updatedCustomers){
		CustomersDto customersDto = customerService.updateCustomer(customerId, updatedCustomers);
		return ResponseEntity.ok(customersDto);
	}
}
