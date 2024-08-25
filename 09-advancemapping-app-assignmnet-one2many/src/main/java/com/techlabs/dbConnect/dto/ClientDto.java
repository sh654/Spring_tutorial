package com.techlabs.dbConnect.dto;

import java.time.LocalDate;
import java.util.List;

import com.techlabs.dbConnect.entity.Employee;
import com.techlabs.dbConnect.enums.KycStatus;
import com.techlabs.dbConnect.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ClientDto {

	private int clientId;

	private String companyName;
	
	private String registationNumber;
	
	private String contactPerson;
	
	private String contactEmail;
	
	private String contactNumber;
	
	private String address;

	private Status status;
	
	private LocalDate creationDate;
	
	private KycStatus kycStatus;
	
	private List<Employee> employee;
	
}
