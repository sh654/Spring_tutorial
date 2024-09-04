package com.techlabs.dbConnect.service;

import com.techlabs.dbConnect.dtos.CustomersDto;
import com.techlabs.dbConnect.dtos.PageResponse;
import com.techlabs.dbConnect.entity.Customers;

public interface CustomerService {

	CustomersDto addCustomer(Customers customers);
	CustomersDto getCustomerById(int customerId);
	CustomersDto updateCustomer(int customerId, Customers customer);
	void deleteCustomer(int customerId);
	PageResponse<CustomersDto> getAllCustomer(int pageNo, int pageSize);
}
