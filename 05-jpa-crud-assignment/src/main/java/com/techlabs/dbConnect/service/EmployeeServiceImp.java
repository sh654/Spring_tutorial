package com.techlabs.dbConnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.entity.Employee;
import com.techlabs.dbConnect.repository.EmployeeRepository;

@Service
public class EmployeeServiceImp implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Override
	public Page<Employee> getAllEmployees(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return employeeRepo.findAll(pageable);
	}

	@Override
	public void addEmployees(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmployees(int employeeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEmployees(Employee employee, int employeeId) {
		// TODO Auto-generated method stub
		
	}

}
