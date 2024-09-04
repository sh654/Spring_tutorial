package com.techlabs.dbConnect.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dtos.AccountsDto;
import com.techlabs.dbConnect.dtos.PageResponse;
import com.techlabs.dbConnect.dtos.TransactionDto;
import com.techlabs.dbConnect.entity.Accounts;
import com.techlabs.dbConnect.entity.Customers;
import com.techlabs.dbConnect.entity.Transactions;
import com.techlabs.dbConnect.enums.TransactionType;
import com.techlabs.dbConnect.repository.AccountRepository;
import com.techlabs.dbConnect.repository.CustomerRepository;
import com.techlabs.dbConnect.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionsRepository;

    @Autowired
    private AccountRepository accountsRepository;
    
    @Autowired
    private CustomerRepository customerRepo;

    @Override
    public PageResponse<TransactionDto> getSenderTransaction(int pageNo, int pageSize, Long senderAccountNumber) {

        // Create a pageable object
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        // Find all transactions by sender account number with pagination
        Page<Transactions> userTransactions = transactionsRepository.findBySenderAccountNumber(senderAccountNumber, pageable);

        // Map entities to DTOs
        List<TransactionDto> transactionList = userTransactions.stream()
                .map(this::toDtoMapper)
                .collect(Collectors.toList());

        // Create a page response object
        PageResponse<TransactionDto> response = new PageResponse<>();
        response.setSize(userTransactions.getSize());
        response.setTotalElements(userTransactions.getTotalElements());
        response.setTotalPages(userTransactions.getTotalPages());
        response.setContents(transactionList);
        response.setLastPage(userTransactions.isLast());

        return response;
    }



	@Override
	public PageResponse<TransactionDto> getAllTransactions(int pageNo, int pageSize) {
		Pageable pagebale = PageRequest.of(pageNo, pageSize);
		Page<Transactions> transactionPage = transactionsRepository.findAll(pagebale);
		List<TransactionDto> transactionList = transactionPage.stream()
				.map(this::toDtoMapper)
				.collect(Collectors.toList());
		PageResponse<TransactionDto> response = new PageResponse<>();
		response.setSize(transactionPage.getSize());
		response.setTotalElements(transactionPage.getTotalElements());
		response.setTotalPages(transactionPage.getTotalPages());
		response.setContents(transactionList);
		response.setLastPage(transactionPage.isLast());
		return response;
	}
	
	private TransactionDto toDtoMapper(Transactions transactions) {
		TransactionDto transaction = new TransactionDto();
		transaction.setTransactionId(transactions.getTransactionId());
		transaction.setTransactionType(transactions.getTransactionType());
		transaction.setAmount(transactions.getAmount());
		transaction.setSenderAccountNumber(transactions.getSenderAccountNumber());
		transaction.setReceiverAccountNumber(transactions.getReceiverAccountNumber());
		return transaction;
	}



	@Override
	public PageResponse<TransactionDto> getAccountTransaction(int pageNo, int pageSize, Long senderAccountNumber) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		System.out.println(username);
		
		boolean isAdmin = authentication.getAuthorities()
				.stream()
				.anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
		System.out.println(isAdmin);
		System.out.println(isAdmin);
		System.out.println(isAdmin);
		
		// fetch customer entity 
		Customers currentCustomer = customerRepo.findByUser_UserName(username)
				.orElseThrow(()-> new RuntimeException("Current User not Found"));
		 System.out.println("Current Customer: " + currentCustomer);
		 //List of account for above user
		 
		 	 
		 List<Long> accountNumbers = currentCustomer.getAccounts()
		            .stream()
		            .map(Accounts::getAccountNumber)
		            .collect(Collectors.toList());
		    System.out.println("Account Numbers for Current Customer: " + accountNumbers);
		    

		
		System.out.println(currentCustomer.getAccounts().stream().anyMatch(account -> account.getAccountNumber().equals(senderAccountNumber)));
		
		if (isAdmin || accountNumbers.contains(senderAccountNumber)) {
	        
			Page<Transactions> userTransactions = transactionsRepository.findBySenderAccountNumberOrReceiverAccountNumber(senderAccountNumber, senderAccountNumber, pageable);

		    // Map entities to DTOs
		    List<TransactionDto> transactionList = userTransactions.stream()
		            .map(this::toDtoMapper)
		            .collect(Collectors.toList());

		    // Create a page response object
		    PageResponse<TransactionDto> response = new PageResponse<>();
		    response.setSize(userTransactions.getSize());
		    response.setTotalElements(userTransactions.getTotalElements());
		    response.setTotalPages(userTransactions.getTotalPages());
		    response.setContents(transactionList);
		    response.setLastPage(userTransactions.isLast());

		    return response;
	    }
		else {
			throw new SecurityException("You are not authorized to view transactions for this account");
		}
		 
	}
	
	
}
