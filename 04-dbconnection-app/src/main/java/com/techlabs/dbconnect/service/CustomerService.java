package com.techlabs.dbconnect.service;

import java.util.List;

import com.techlabs.dbconnect.entity.Customer;

public interface CustomerService {

	List<Customer> getAllCustomer();
	void addCustomer(Customer customer);
	Customer getCustomer(int customerid);
	void deleteCustomer(int customerid);
	void updateCustomer(Customer customer);
}
