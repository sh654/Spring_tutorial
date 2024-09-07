package com.techlabs.dbConnect.service;

import java.io.ByteArrayInputStream;

import com.techlabs.dbConnect.dtos.AccountsDto;
import com.techlabs.dbConnect.dtos.PageResponse;
import com.techlabs.dbConnect.dtos.TransactionDto;
import com.techlabs.dbConnect.enums.TransactionType;

public interface TransactionService {
//    TransactionDto addTransaction(TransactionDto transactionsDto);
    PageResponse<TransactionDto> getSenderTransaction(int pageNo, int pageSize, long senderAccountNumber);
    PageResponse<TransactionDto> getAllTransactions(int pageNo, int pageSize);
    PageResponse<TransactionDto> getAccountTransaction(int pageNo, int pageSize, long accountNumber);
    public ByteArrayInputStream exportTransactionToPdf(long accountNumber, TransactionType type);
}

