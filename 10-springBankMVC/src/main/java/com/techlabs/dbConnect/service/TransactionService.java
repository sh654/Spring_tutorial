package com.techlabs.dbConnect.service;

import com.techlabs.dbConnect.dtos.AccountsDto;
import com.techlabs.dbConnect.dtos.PageResponse;
import com.techlabs.dbConnect.dtos.TransactionDto;

public interface TransactionService {
//    TransactionDto addTransaction(TransactionDto transactionsDto);
    PageResponse<TransactionDto> getSenderTransaction(int pageNo, int pageSize, long senderAccountNumber);
    PageResponse<TransactionDto> getAllTransactions(int pageNo, int pageSize);
    PageResponse<TransactionDto> getAccountTransaction(int pageNo, int pageSize, long accountNumber);
}

