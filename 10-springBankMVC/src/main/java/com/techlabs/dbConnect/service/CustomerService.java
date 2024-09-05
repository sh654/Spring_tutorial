package com.techlabs.dbConnect.service;

import java.util.List;

import com.techlabs.dbConnect.dtos.CustomersDto;
import com.techlabs.dbConnect.dtos.PageResponse;
import com.techlabs.dbConnect.entity.Accounts;
import com.techlabs.dbConnect.entity.Customers;

public interface CustomerService {

	CustomersDto addCustomer(Customers customers);
	CustomersDto getCustomerById(int customerId);
	CustomersDto updateCustomer(int customerId, Customers customer);
	void deleteCustomer(int customerId);
	CustomersDto assignAccounts(Customers customers, List<Accounts> accounts);
	PageResponse<CustomersDto> getAllCustomer(int pageNo, int pageSize);
}
