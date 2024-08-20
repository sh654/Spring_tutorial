package com.techlabs.dbconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbconnect.entity.Customer;
import com.techlabs.dbconnect.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImp implements CustomerService{

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		
		return customerRepo.getAllCustomer();
	}

	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepo.addCustomer(customer);
	}

	@Override
	public Customer getCustomer(int customerid) {
		// TODO Auto-generated method stub
		return customerRepo.getCustomer(customerid);
	}

	@Override
	@Transactional
	public void deleteCustomer(int customerid) {
		// TODO Auto-generated method stub
		customerRepo.deleteCustomer(customerid);
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepo.updateCustomer(customer);
	}

}
