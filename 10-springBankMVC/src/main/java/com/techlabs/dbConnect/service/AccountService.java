package com.techlabs.dbConnect.service;

import java.util.List;

import com.techlabs.dbConnect.dtos.AccountsDto;
import com.techlabs.dbConnect.dtos.PageResponse;
import com.techlabs.dbConnect.entity.Accounts;
import com.techlabs.dbConnect.entity.Customers;
import com.techlabs.dbConnect.enums.TransactionType;

public interface AccountService {
    AccountsDto addAccount(Accounts accounts);
    AccountsDto getAccountByAccountNumber(long accountNumber);
    AccountsDto getAccountById(int accountId);
    AccountsDto updateAccount(int accountId, AccountsDto accountsDto);
    void deleteAccount(int accountId);
    List<AccountsDto> getAccountsByCustomerId(int customerId);
    PageResponse<AccountsDto> getAllAccounts(int pageNo, int pageSize);
    
    void transfer(Long accountNumber, Long receiverAccountNumber, double amount);
    void debit(Long accountNumber, Long receiverAccountNumber, double amount, TransactionType transType);
    void credit(Long accountNumber, Long receiverAccountNumber, double amount, TransactionType transType);
}

