package com.techlabs.dbConnect.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dtos.CustomersDto;
import com.techlabs.dbConnect.dtos.PageResponse;
import com.techlabs.dbConnect.entity.Customers;
import com.techlabs.dbConnect.repository.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService{

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public CustomersDto addCustomer(Customers customers) {
		// TODO Auto-generated method stub
		customers = customerRepo.save(customers);
		return toCustomersDtoMapper(customers);
	}

	@Override
	public CustomersDto getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		
		Customers customers = customerRepo.findById(customerId).orElseThrow(()-> new RuntimeException("Customer not found"));
		CustomersDto customerDto = toCustomersDtoMapper(customers);
		
		return customerDto;
	}

	@Override
	public CustomersDto updateCustomer(int customerId, Customers updatedCustomer) {
	    // Find the existing customer by ID
	    Customers existingCustomer = customerRepo.findById(customerId)
	            .orElseThrow(() -> new RuntimeException("Customer not found"));
	    // Update the fields
	    existingCustomer.setFirstName(updatedCustomer.getFirstName());
	    existingCustomer.setLastName(updatedCustomer.getLastName());
	    existingCustomer.setEmail(updatedCustomer.getEmail());
	    existingCustomer.setPhone(updatedCustomer.getPhone());
	    existingCustomer.setAddress(updatedCustomer.getAddress());
	  // Save the updated customer
	    Customers savedCustomer = customerRepo.save(existingCustomer);
	    
	    // Convert to DTO and return
	    return toCustomersDtoMapper(savedCustomer);
	}


	@Override
	public void deleteCustomer(int customerId) {
		customerRepo.deleteById(customerId);
	}

	@Override
	public PageResponse<CustomersDto> getAllCustomer(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = PageRequest.of(pageNo, pageSize);
		Page<Customers> customerPage = customerRepo.findAll(pageable);
		
		// Convert customer entity to DTO
		List<CustomersDto> customersDto = customerPage.stream()
				.map(this :: toCustomersDtoMapper).collect(Collectors.toList());
		
		PageResponse<CustomersDto> pageResponse = new PageResponse<>();
	    pageResponse.setSize(customerPage.getSize());
	    pageResponse.setTotalElements(customerPage.getTotalElements());
	    pageResponse.setTotalPages(customerPage.getTotalPages());
	    pageResponse.setLastPage(customerPage.isLast());
	    pageResponse.setContents(customersDto);
		
		return pageResponse;
	}
	
	private Customers toCustomerMapper(CustomersDto customersDto) {
		Customers customers = new Customers();
		customers.setFirstName(customersDto.getFirstName());
		customers.setLastName(customersDto.getLastName());
		customers.setEmail(customersDto.getEmail());
		customers.setPhone(customersDto.getPhone());
		customers.setAddress(customersDto.getAddress());
		customers.setCreatedAt(customersDto.getCreatedAt());
		customers.setUpdatedAt(customersDto.getUpdatedAt());
		
		return customers;
	}
	
	private CustomersDto toCustomersDtoMapper(Customers customers) {
	    CustomersDto customersDto = new CustomersDto();
	    customersDto.setCustomerId(customers.getCustomerId());  // Set the customerId
	    customersDto.setFirstName(customers.getFirstName());
	    customersDto.setLastName(customers.getLastName());
	    customersDto.setEmail(customers.getEmail());
	    customersDto.setPhone(customers.getPhone());
	    customersDto.setAddress(customers.getAddress());
	    customersDto.setCreatedAt(customers.getCreatedAt());
	    customersDto.setUpdatedAt(customers.getUpdatedAt());
	    return customersDto;
	}


}
