package com.techlabs.dbConnect.service;

import com.techlabs.dbConnect.dto.EmployeeDto;
import com.techlabs.dbConnect.dto.ResponseDto;
import com.techlabs.dbConnect.dto.SalaryAccountDto;
import com.techlabs.dbConnect.entity.Employee;
import com.techlabs.dbConnect.entity.SalaryAccount;

public interface EmployeeService {

	ResponseDto<Employee> getAllEmployees(String firstName, int pagenNo, int pageSize);
	ResponseDto<EmployeeDto> getAllEmployeesDto(String firstName, int pageNo, int pageSize);
	ResponseDto<SalaryAccountDto> getSalaryAccountByNumber(long accountNumber);
	Employee addStudentDetails(Employee employee);
	
	SalaryAccount updateSalaryAccount(int employeeId, SalaryAccount salaryAccount);
	void deleteByAccountNumber(long accountNumber);
}
