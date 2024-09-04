package com.techlabs.dbConnect.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dtos.AccountsDto;

import com.techlabs.dbConnect.dtos.PageResponse;
import com.techlabs.dbConnect.entity.Accounts;
import com.techlabs.dbConnect.entity.Customers;
import com.techlabs.dbConnect.entity.Transactions;
import com.techlabs.dbConnect.enums.TransactionType;
import com.techlabs.dbConnect.repository.AccountRepository;
import com.techlabs.dbConnect.repository.CustomerRepository;
import com.techlabs.dbConnect.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountServiceImp implements AccountService{

	@Autowired
    private AccountRepository accountsRepo;

    @Autowired
    private CustomerRepository customersRepo;
    
    @Autowired
    private TransactionRepository transactionRepo;
	
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
		
		return toAccountDtoMapper(account);
	}

	private long generateRandomAccountNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextLong(1000000000L, 10000000000L); // Generates a 10-digit long
    }


	@Override
	public AccountsDto getAccountById(int accountId) {
		// TODO Auto-generated method stub
		Accounts accounts = accountsRepo.findById(accountId)
				.orElseThrow(() -> new RuntimeException("Account Not Found"));
		
		return toAccountDtoMapper(accounts);
	}
	
	@Override
	public AccountsDto getAccountByAccountNumber(long accountNumber) {
		Accounts accounts = accountsRepo.findByAccountNumber(accountNumber)
				.orElseThrow(()-> new RuntimeException("No account number exists!"));
		
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
		// TODO Auto-generated method stub
		Accounts fromAccountNum = getAccount(accountNumber);
		
		Accounts toAccountNum = getAccount(receiverAccountNumber);
		
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
		// TODO Auto-generated method stub
		Accounts toAccount= getAccount(accountNumber);
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
	}
	
	@Override
	@Transactional
	public void debit(Long accountNumber, Long receiverAccountNumber, double amount, TransactionType transType) {
		// TODO Auto-generated method stub
		Accounts fromAccount = getAccount(accountNumber);
		
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
	}
	
	private Accounts getAccount(long accountNumber) {
		return accountsRepo.findByAccountNumber(accountNumber).orElseThrow(()-> 
		new RuntimeException("Account not found"));
	}

	
	
}
