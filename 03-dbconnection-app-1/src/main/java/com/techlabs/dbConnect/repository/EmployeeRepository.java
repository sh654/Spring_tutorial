package com.techlabs.dbConnect.repository;

import java.util.List;

import com.techlabs.dbConnect.entity.Employee;

public interface EmployeeRepository {

	List<Employee> getAllEmployee();
	List<Employee> getAllEmployeeByName(String name);
	Employee getEmployee(int empid);
	void addEmployee(Employee employee);
	void deleteEmployee(int empid);
	
}
