package com.techlabs.dbConnect.service;

import java.util.List;

import com.techlabs.dbConnect.entity.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployee();

	
	void addEmployee(Employee employee);
	
	Employee getEmployee(int empid);
	
	void deleteEmployee(int empid);
	
	List<Employee> getEmployeeByName(String name);
	
}
