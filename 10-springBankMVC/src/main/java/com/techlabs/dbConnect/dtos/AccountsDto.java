package com.techlabs.dbConnect.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techlabs.dbConnect.entity.Customers;
import com.techlabs.dbConnect.enums.AccountType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class AccountsDto {

	private int accountId;
	@NotNull(message = "Account number is required")
    @Pattern(regexp = "\\d{10}", message = "Account number must be exactly 10 digits")
	private long accountNumber;
	@NotNull(message = "Account type is required")
	private AccountType accountType;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	@Min(value = 1, message = "Amount must be positive")
	private double amount;
	
	private Customers customers;

	private CustomersDto customer;
}

