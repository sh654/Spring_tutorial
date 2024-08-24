package com.techlabs.dbConnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class SalaryAccountDto {

	private long accountNumber;
    private String accountHolderName;
	
}
