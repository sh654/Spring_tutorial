package com.techlabs.dbConnect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dto.EmployeeDto;
import com.techlabs.dbConnect.dto.SalaryAccountDto;
import com.techlabs.dbConnect.entity.Client;
import com.techlabs.dbConnect.entity.Employee;
import com.techlabs.dbConnect.entity.SalaryAccount;
import com.techlabs.dbConnect.repository.ClientRepo;
import com.techlabs.dbConnect.repository.EmployeeRepo;


@Service
public class EmployeeServiceImp implements EmployeeService{
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private ClientRepo clientRepo;

	@Override
	public Employee addEmployees(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		Employee employee = new Employee();
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setPhoneNumber(employeeDto.getPhoneNumber());
		employee.setEmployeeEmail(employeeDto.getEmployeeEmail());
		employee.setEmployeeHireDate(employeeDto.getEmployeeHireDate());
		employee.setEmployeePosition(employeeDto.getEmployeePosition());
		employee.setEmployeeSalary(employeeDto.getEmployeeSalary());
		employee.setEmployeeStatus(employeeDto.getEmployeeStatus());
		
		return employeeRepo.save(employee);
	}

	@Override
	public Employee allocateClient(int employeeId, Client client) {
		// TODO Auto-generated method stub
		Optional<Employee> employeeOptional = employeeRepo.findById(employeeId);
		if(!employeeOptional.isPresent())
			return null;
		
		Employee employeeDb = employeeOptional.get();
		
		Client clientDb;
		Optional<Client> clientOptional = clientRepo.findById(client.getClientId());
		if(!clientOptional.isPresent())
			return null;
		
		clientDb = clientOptional.get();
		
		employeeDb.setClient(clientDb);
		
		return employeeRepo.save(employeeDb);
	}

	
}
