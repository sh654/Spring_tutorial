package com.techlabs.dbConnect.service;

import org.springframework.data.domain.Page;

import com.techlabs.dbConnect.entity.Employee;

public interface EmployeeService {
	Page<Employee> getAllEmployees(int pageNo, int pageSize);
	void addEmployees(Employee employee);
	void deleteEmployees(int employeeId);
	void updateEmployees(Employee employee, int employeeId);
	
}
