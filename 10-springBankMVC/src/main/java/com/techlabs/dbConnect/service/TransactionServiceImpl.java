package com.techlabs.dbConnect.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dtos.AccountsDto;
import com.techlabs.dbConnect.dtos.PageResponse;
import com.techlabs.dbConnect.dtos.TransactionDto;
import com.techlabs.dbConnect.entity.Accounts;
import com.techlabs.dbConnect.entity.Customers;
import com.techlabs.dbConnect.entity.EmailDetails;
import com.techlabs.dbConnect.entity.Transactions;
import com.techlabs.dbConnect.entity.Users;
import com.techlabs.dbConnect.enums.TransactionType;
import com.techlabs.dbConnect.exception.UserApiException;
import com.techlabs.dbConnect.repository.AccountRepository;
import com.techlabs.dbConnect.repository.CustomerRepository;
import com.techlabs.dbConnect.repository.TransactionRepository;
import com.techlabs.dbConnect.repository.UserRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionsRepository;

    @Autowired
    private AccountRepository accountsRepository;
    
    @Autowired
    private CustomerRepository customerRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private EmailService emailService;

    @Override
    public PageResponse<TransactionDto> getSenderTransaction(int pageNo, int pageSize, long senderAccountNumber) {

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

    
    private EmailDetails setEmailDetails(Accounts accounts) {
	    EmailDetails emailDetails = new EmailDetails();
	    emailDetails.setRecipient(accounts.getCustomer().getUser().getUserName());
	    emailDetails.setSubject("Customer Registration Successfull");
	    emailDetails.setMsgBody("Welcome Customer : "+accounts.getCustomer().getFirstName() +" " + accounts.getCustomer().getLastName() +
	    "\n Your Account Id : "+accounts.getAccountId() +
	    "\n Your Account Number : " +accounts.getAccountNumber()
	    +"\n Your Account Type : "+accounts.getAccountType() +"\n Your Balance : "+accounts.getAmount());
	    return emailDetails;
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
	public PageResponse<TransactionDto> getAccountTransaction(int pageNo, int pageSize, long accountNumber) {
	    // Get the authenticated user
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();

	    // Retrieve the user details from the repository
	    Users user = userRepo.findByUserName(currentUsername)
	            .orElseThrow(() -> new UserApiException(HttpStatus.NOT_FOUND, "User not found"));

	    // Check if the user is an admin
	    boolean isAdmin = user.getRoles().stream()
	            .anyMatch(role -> role.getRoleName().equals("ROLE_ADMIN"));

	    // If the user is not an admin, check if the account number belongs to the user
	    if (!isAdmin) {
	        Optional<Accounts> userAccount = accountsRepository.findByAccountNumberAndCustomer_User_UserName(accountNumber, currentUsername);
	        if (userAccount.isEmpty()) {
	            throw new UserApiException(HttpStatus.FORBIDDEN, "You are not authorized to view this account's transactions");
	        }
	    }

	    // Create pageable object
	    Pageable pageable = PageRequest.of(pageNo, pageSize);

	    // Fetch transactions for the specified account number
	    Page<Transactions> transactionsPage = transactionsRepository.findBySenderAccountNumber(accountNumber, pageable);

	    // Map entities to DTOs
	    List<TransactionDto> transactionList = transactionsPage.stream()
	            .map(this::toDtoMapper)
	            .collect(Collectors.toList());

	    // Create and return a PageResponse object
	    PageResponse<TransactionDto> response = new PageResponse<>();
	    response.setSize(transactionsPage.getSize());
	    response.setTotalElements(transactionsPage.getTotalElements());
	    response.setTotalPages(transactionsPage.getTotalPages());
	    response.setContents(transactionList);
	    response.setLastPage(transactionsPage.isLast());

	    return response;
	}

	
	
}
