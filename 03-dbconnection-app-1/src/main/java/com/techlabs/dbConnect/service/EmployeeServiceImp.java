package com.techlabs.dbConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.entity.Employee;
import com.techlabs.dbConnect.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImp implements EmployeeService{

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return empRepo.getAllEmployee();
	}

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		empRepo.addEmployee(employee);
	}

	@Override
	public Employee getEmployee(int empid) {
		// TODO Auto-generated method stub
		return empRepo.getEmployee(empid);
	}

	@Override
	@Transactional
	public void deleteEmployee(int empid) {
		// TODO Auto-generated method stub
		empRepo.deleteEmployee(empid);
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		// TODO Auto-generated method stub
		return empRepo.getAllEmployeeByName(name);
	}
	
	
}

