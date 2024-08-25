package com.techlabs.dbConnect.service;

import com.techlabs.dbConnect.dto.EmployeeDto;
import com.techlabs.dbConnect.dto.SalaryAccountDto;
import com.techlabs.dbConnect.entity.Client;
import com.techlabs.dbConnect.entity.Employee;
import com.techlabs.dbConnect.entity.SalaryAccount;

public interface EmployeeService {

	Employee addEmployees(EmployeeDto employeeDto);
	
	Employee allocateClient(int employeeId, Client client);
}
