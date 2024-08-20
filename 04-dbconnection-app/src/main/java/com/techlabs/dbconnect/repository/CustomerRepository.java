package com.techlabs.dbconnect.repository;

import java.util.List;

import com.techlabs.dbconnect.entity.Customer;

public interface CustomerRepository {

	List<Customer> getAllCustomer();
	
	Customer getCustomer(int customerid);
	void addCustomer(Customer customer);
	void deleteCustomer(int customerid);
	void updateCustomer(Customer customer);
	
}
