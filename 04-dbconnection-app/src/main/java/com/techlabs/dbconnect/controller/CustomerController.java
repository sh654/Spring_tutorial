package com.techlabs.dbconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbconnect.entity.Customer;
import com.techlabs.dbconnect.service.CustomerService;

@RequestMapping("/customerapp")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomer(){
		return ResponseEntity.ok(customerService.getAllCustomer());
	}
	
	@PostMapping("/customers")
	public String addCustomer(@RequestBody Customer customer){
		customerService.addCustomer(customer);
		return "Successfully Added";
	}
	
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int customerId){
		customerService.deleteCustomer(customerId);
		return ResponseEntity.ok("Deleted Successfully");
	}
	
	@PutMapping("/customers")
	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer){
		customerService.updateCustomer(customer);
		return ResponseEntity.ok("Updated Successfully");
	}
	
}
