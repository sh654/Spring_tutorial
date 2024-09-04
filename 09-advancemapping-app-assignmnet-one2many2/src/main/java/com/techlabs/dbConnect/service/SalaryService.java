package com.techlabs.dbConnect.service;

import com.techlabs.dbConnect.dto.SalaryDto;
import com.techlabs.dbConnect.entity.Salary;

public interface SalaryService {

	Salary createSalaryForEmployee(int employeeId, double deductions, SalaryDto salaryDto);
	
	Salary updateSalaryAndTransaction(int salaryId, double newDeductions);
	
}
