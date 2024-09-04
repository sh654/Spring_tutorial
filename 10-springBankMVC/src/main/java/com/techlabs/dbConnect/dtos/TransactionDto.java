package com.techlabs.dbConnect.dtos;

import com.techlabs.dbConnect.enums.TransactionType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class TransactionDto {

    private int transactionId;
    @NotNull(message = "Transaction type is required")
    private TransactionType transactionType;
    @Min(value = 1, message = "Amount must be greater than 1")
    private double amount;
    
    private String description;
    @NotNull(message = "Sender account number is required")
    private Long senderAccountNumber;
    @NotNull(message = "Sender account number is required")
    private Long receiverAccountNumber;
    private AccountsDto accountDto;  // Nested DTO for Account
	
    public Long getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(Long receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }
    // Getters and Setters
    // ...
}

