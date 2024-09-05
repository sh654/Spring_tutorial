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
import com.techlabs.dbConnect.entity.Accounts;
import com.techlabs.dbConnect.entity.Customers;
import com.techlabs.dbConnect.entity.EmailDetails;
import com.techlabs.dbConnect.entity.Users;
import com.techlabs.dbConnect.repository.AccountRepository;
import com.techlabs.dbConnect.repository.CustomerRepository;
import com.techlabs.dbConnect.repository.UserRepository;

@Service
public class CustomerServiceImp implements CustomerService{

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public CustomersDto addCustomer(Customers customers) {
		// TODO Auto-generated method stub
		Users existingUser = userRepo.findById(customers.getUser().getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found with id provided"  ));

	    // Attach the managed user entity to the customer
	    customers.setUser(existingUser);
		
		customers = customerRepo.save(customers);
		EmailDetails emailDetails = new EmailDetails();
		emailDetails.setRecipient(customers.getUser().getUserName());
		emailDetails.setSubject("Customer Registration Successfull");
		emailDetails.setMsgBody("Welcome Customer : "+customers.getFirstName() +" " + customers.getLastName() +
		"\n Your Customer Id : "+customers.getCustomerId() +
		"\n Your Deatils are as below : \n Your Phone Number : " +customers.getPhone()
		+"\n Your Address : "+customers.getAddress() +"\n Your Email: "+customers.getEmail());
		
		emailService.sendSimpleEmail(emailDetails);
		return toCustomersDtoMapper(customers);
	}
	
	private EmailDetails setEmailDetails(Customers customers) {
	    EmailDetails emailDetails = new EmailDetails();
	    emailDetails.setRecipient(customers.getUser().getUserName());
	    emailDetails.setSubject("Customer Registration Successfull");
	    emailDetails.setMsgBody("Welcome Customer : "+customers.getFirstName() +" " + customers.getLastName() +
	    "\n Your Customer Id : "+customers.getCustomerId() +
	    "\n Your Deatils are as below : \n Your Phone Number : " +customers.getPhone()
	    +"\n Your Address : "+customers.getAddress() +"\n Your Email: "+customers.getEmail());
	    return emailDetails;
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
	    
	    EmailDetails emailDetails = setEmailDetails(savedCustomer);
	    emailService.sendSimpleEmail(emailDetails); 
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

	@Override
	public CustomersDto assignAccounts(Customers customers, List<Accounts> accounts) {
		
		return null;
	}


}
