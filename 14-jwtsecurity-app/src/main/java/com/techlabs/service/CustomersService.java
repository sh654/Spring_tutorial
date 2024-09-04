package com.techlabs.service;

import java.util.List;

import com.techlabs.entity.Customers;

public interface CustomersService {

	List<Customers> getAllCustomers();
	Customers getCustomerById(int customerId);
}
