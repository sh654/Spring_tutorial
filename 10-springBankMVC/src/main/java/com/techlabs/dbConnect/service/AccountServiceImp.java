package com.techlabs.dbConnect.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dtos.AccountsDto;

import com.techlabs.dbConnect.dtos.PageResponse;
import com.techlabs.dbConnect.entity.Accounts;
import com.techlabs.dbConnect.entity.Customers;
import com.techlabs.dbConnect.entity.EmailDetails;
import com.techlabs.dbConnect.entity.Transactions;
import com.techlabs.dbConnect.entity.Users;
import com.techlabs.dbConnect.enums.TransactionType;
import com.techlabs.dbConnect.repository.AccountRepository;
import com.techlabs.dbConnect.repository.CustomerRepository;
import com.techlabs.dbConnect.repository.TransactionRepository;
import com.techlabs.dbConnect.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountServiceImp implements AccountService{

	@Autowired
    private AccountRepository accountsRepo;

    @Autowired
    private CustomerRepository customersRepo;
    
    @Autowired
    private TransactionRepository transactionRepo;
	
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private UserRepository userRepo;
    
	@Override
	public AccountsDto addAccount(Accounts accounts) {
		// TODO Auto-generated method stub
		Accounts account = new Accounts();
		Customers customer = customersRepo.findById(accounts.getCustomer().getCustomerId())
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		
		
		long accountNumber = generateRandomAccountNumber();
		
		account.setCustomer(customer);
		account.setAccountNumber(accountNumber);
		account.setAccountType(accounts.getAccountType());
		account.setAmount(accounts.getAmount());
		
		account = accountsRepo.save(account);
		EmailDetails emailDetails = setEmailDetails(account);
		emailService.sendSimpleEmail(emailDetails);
		return toAccountDtoMapper(account);
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
	
	private EmailDetails setTransactionMail(Accounts account, Transactions transaction) {
	    EmailDetails emailDetails = new EmailDetails();
	    
	    emailDetails.setRecipient(account.getCustomer().getUser().getUserName());
	    emailDetails.setSubject("Transaction Successful");
	    
	    StringBuilder msgBody = new StringBuilder();
	    msgBody.append("Hello, ")
	           .append(account.getCustomer().getFirstName())
	           .append(" ")
	           .append(account.getCustomer().getLastName())
	           .append(",\n\n")
	           .append("We would like to inform you that a transaction has been successfully completed.\n")
	           .append("Here are the details:\n\n")
	           .append("Transaction ID: ").append(transaction.getTransactionId()).append("\n")
	           .append("Transaction Type: ").append(transaction.getTransactionType()).append("\n")
	           .append("Transaction Amount: ").append(transaction.getAmount()).append("\n");
	    if (transaction.getTransactionType().equals(TransactionType.TRANSFER)) {
	        msgBody.append("Receiver Account Number: ")
	               .append(transaction.getReceiverAccountNumber())  // Assuming you have a `getReceiverAccountNumber()` method
	               .append("\n\n");
	    }
	    msgBody.append("Transaction Date & Time: ").append(transaction.getCreatedAt()).append("\n\n")
        .append("Your Account Information:\n")
        .append("Account Number: ").append(account.getAccountNumber()).append("\n")
        .append("Account Type: ").append(account.getAccountType()).append("\n")
        .append("Current Balance: ").append(account.getAmount()).append("\n\n")
        .append("Thank you for banking with us!\n")
        .append("Best regards,\nYour Bank");
	    
	    emailDetails.setMsgBody(msgBody.toString());
	    return emailDetails;
	}

	
	private long generateRandomAccountNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextLong(1000000000L, 10000000000L); // Generates a 10-digit long
    }
	
	 @Override
	    public List<AccountsDto> getAccountsByCustomerId(int customerId) {
	        // Fetch the customer entity by ID
	        Customers customer = customersRepo.findById(customerId)
	                .orElseThrow(() -> new RuntimeException("Customer not found"));

	        // Retrieve all accounts linked to this customer
	       List<Accounts> accounts = customer.getAccounts();

	        System.out.println(accounts.size() + "This is accounts size");
	        // Convert the set of accounts to a list of AccountsDto
	        return accounts.stream()
	                       .map(this::toAccountDtoMapper)
	                       .collect(Collectors.toList());
	        
	    }

	@Override
	public AccountsDto getAccountById(int accountId) {
		// TODO Auto-generated method stub
		Accounts accounts = accountsRepo.findById(accountId)
				.orElseThrow(() -> new RuntimeException("Account Not Found"));
		System.out.println(accounts.getCustomer().getFirstName() +"bvernoenib");
		return toAccountDtoMapper(accounts);
	}
	
	@Override
	public AccountsDto getAccountByAccountNumber(long accountNumber) {
		Accounts accounts = accountsRepo.findByAccountNumber(accountNumber)
				.orElseThrow(()-> new RuntimeException("No account number exists!"));
		System.out.println(accounts.getCustomer().getFirstName() +"bvernoenib");
		System.out.println(accounts.getCustomer().getAccounts().size());
		return toAccountDtoMapper(accounts);
	}

	@Override
	public AccountsDto updateAccount(int accountId, AccountsDto accountsDto) {
		// TODO Auto-generated method stub
		Accounts accounts = accountsRepo.findById(accountId)
				.orElseThrow(()-> new RuntimeException());
		Customers customer = customersRepo.findById(accountsDto.getCustomer().getCustomerId())
				.orElseThrow(()-> new RuntimeException(""));
		
		accounts.setCustomer(customer);
		accounts.setAccountType(accountsDto.getAccountType());
		accounts.setAmount(accountsDto.getAmount());
		accounts = accountsRepo.save(accounts);
		return toAccountDtoMapper(accounts);
	}

	@Override
	public void deleteAccount(int accountId) {
        accountsRepo.deleteById(accountId);
    }
	
	

	@Override
	public PageResponse<AccountsDto> getAllAccounts(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Accounts> accountPage = accountsRepo.findAll(pageable);

        List<AccountsDto> accountsDtoList = accountPage.stream()
                .map(this::toAccountDtoMapper)
                .collect(Collectors.toList());

        PageResponse<AccountsDto> pageResponse = new PageResponse<>();
        pageResponse.setSize(accountPage.getSize());
        pageResponse.setTotalElements(accountPage.getTotalElements());
        pageResponse.setTotalPages(accountPage.getTotalPages());
        pageResponse.setLastPage(accountPage.isLast());
        pageResponse.setContents(accountsDtoList);

        return pageResponse;
	}

	
	private Accounts toAccountMapper(AccountsDto accountsDto) {
		Accounts accounts = new Accounts();
		accounts.setAccountNumber(accountsDto.getAccountNumber());
		accounts.setAccountType(accountsDto.getAccountType());
		accounts.setAmount(accountsDto.getAmount());
		accounts.setCreatedAt(accounts.getCreatedAt());
		accounts.setUpdatedAt(accountsDto.getUpdatedAt());
		
		return accounts;
	}
	
	private AccountsDto toAccountDtoMapper(Accounts accounts) {
		AccountsDto accountsDto = new AccountsDto();
		accountsDto.setAccountId(accounts.getAccountId());
		accountsDto.setAccountNumber(accounts.getAccountNumber());
		accountsDto.setAccountType(accounts.getAccountType());
		accountsDto.setAmount(accounts.getAmount());
		accountsDto.setCreatedAt(accounts.getCreatedAt());
		accountsDto.setUpdatedAt(accounts.getUpdatedAt());
		
		return accountsDto;
	}

	@Override
	@Transactional
	public void transfer(Long accountNumber, Long receiverAccountNumber, double amount) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
		Accounts fromAccountNum = getAccount(accountNumber);
		
		Accounts toAccountNum = getAccount(receiverAccountNumber);
		
		Users currentUser = userRepo.findByUserName(username)
	            .orElseThrow(() -> new RuntimeException("Current user not found"));

	    // Check if the account belongs to the current user
	    if (!fromAccountNum.getCustomer().getUser().equals(currentUser)) {
	        throw new RuntimeException("You are not authorized to perform transactions on this account");
	    }
		
		if (accountNumber.equals(receiverAccountNumber)) {
	        throw new RuntimeException("Transfer cannot be made to the same account.");
	    }
		
		if(fromAccountNum.getAmount() - amount >= 1000) {
			debit(accountNumber, receiverAccountNumber, amount, TransactionType.DEBIT);
			credit(accountNumber, receiverAccountNumber, amount, TransactionType.CREDIT);
			
		} else {
			throw new RuntimeException("Insufficient Balance boi!!!");
		}
	}

	@Override
	@Transactional
	public void credit(Long accountNumber, Long receiverAccountNumber, double amount, TransactionType transType) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
		Accounts toAccount= getAccount(accountNumber);
		
		 Users currentUser = userRepo.findByUserName(username)
		            .orElseThrow(() -> new RuntimeException("Current user not found"));

		    // Check if the account belongs to the current user
		    if (!toAccount.getCustomer().getUser().equals(currentUser)) {
		        throw new RuntimeException("You are not authorized to perform transactions on this account");
		    }
		toAccount.setAmount(toAccount.getAmount() + amount);
		accountsRepo.save(toAccount);
		
		Transactions transaction = new Transactions();
		transaction.setAccount(toAccount);
		transaction.setSenderAccountNumber(accountNumber);
		transaction.setReceiverAccountNumber(receiverAccountNumber);
		transaction.setAmount(amount);
		transaction.setTransactionType(transType);
		transaction.setCreatedAt(toAccount.getCreatedAt());
		transactionRepo.save(transaction);
		
		 EmailDetails emailDetails = setTransactionMail(toAccount, transaction);
		 emailService.sendSimpleEmail(emailDetails);
	}
	
	@Override
	@Transactional
	public void debit(Long accountNumber, Long receiverAccountNumber, double amount, TransactionType transType) {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName(); // Assuming username is used to fetch user info

	    // Fetch the account to be debited
	    Accounts fromAccount = getAccount(accountNumber);
	    
	    // Fetch the current user's information
	    Users currentUser = userRepo.findByUserName(username)
	            .orElseThrow(() -> new RuntimeException("Current user not found"));

	    // Check if the account belongs to the current user
	    if (!fromAccount.getCustomer().getUser().equals(currentUser)) {
	        throw new RuntimeException("You are not authorized to perform transactions on this account");
	    }

	    // Perform the transaction
	    fromAccount.setAmount(fromAccount.getAmount() - amount);
	    accountsRepo.save(fromAccount);

	    Transactions transaction = new Transactions();
	    transaction.setAccount(fromAccount);
	    transaction.setSenderAccountNumber(accountNumber);
	    transaction.setAmount(amount);
	    transaction.setReceiverAccountNumber(receiverAccountNumber);
	    transaction.setTransactionType(transType);
	    transaction.setCreatedAt(fromAccount.getCreatedAt());
	    transactionRepo.save(transaction);

	    EmailDetails emailDetails = setTransactionMail(fromAccount, transaction);
	    emailService.sendSimpleEmail(emailDetails);
	}
	
	private Accounts getAccount(long accountNumber) {
		return accountsRepo.findByAccountNumber(accountNumber).orElseThrow(()-> 
		new RuntimeException("Account not found"));
	}

	

	
	
}
