package com.techlabs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.entity.Customers;
import com.techlabs.repository.CustomersRepository;

@Service
public class CustomersServiceImp implements CustomersService{

	@Autowired
	private CustomersRepository customerRepo;
	@Override
	public List<Customers> getAllCustomers() {
		
		return customerRepo.findAll();
	}

	@Override
	public Customers getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		Customers customers = customerRepo.findById(customerId)
				.orElseThrow(()-> new RuntimeException("Customer not found"));
		
		return customers;
		
	}

	
	
}
