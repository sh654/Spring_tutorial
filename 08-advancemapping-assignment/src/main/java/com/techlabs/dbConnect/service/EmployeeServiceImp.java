package com.techlabs.dbConnect.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dto.EmployeeDto;
import com.techlabs.dbConnect.dto.ResponseDto;
import com.techlabs.dbConnect.dto.SalaryAccountDto;
import com.techlabs.dbConnect.entity.Employee;
import com.techlabs.dbConnect.entity.SalaryAccount;
import com.techlabs.dbConnect.repository.EmployeeRepository;
import com.techlabs.dbConnect.repository.SalaryAccountRepository;

@Service
public class EmployeeServiceImp implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private SalaryAccountRepository salaryAccountRepo;
	
	@Override
	public Employee addStudentDetails(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepo.save(employee);
	}


	@Override
	public ResponseDto<Employee> getAllEmployees(String firstName, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Employee> employeePage;

		if (firstName != null && !firstName.isEmpty()) {
			employeePage = employeeRepo.findByFirstName(firstName, pageable);
		} else {
			employeePage = employeeRepo.findAll(pageable);
		}
		
		ResponseDto<Employee> employeePageResponse = new ResponseDto<>();
		employeePageResponse.setSize(employeePage.getSize());
		employeePageResponse.setTotalElements(employeePage.getTotalElements());
		employeePageResponse.setTotalPages(employeePage.getTotalPages());
		employeePageResponse.setContent(employeePage.getContent());
		employeePageResponse.setLastPage(employeePage.isLast());
		
		return employeePageResponse;
	}
	
	 @Override
	    public ResponseDto<EmployeeDto> getAllEmployeesDto(String firstName, int pageNo, int pageSize) {
	        Pageable pageable = PageRequest.of(pageNo, pageSize);
	        Page<Employee> employeePage = (firstName != null) ? 
	            employeeRepo.findByFirstName(firstName, pageable) : 
	            employeeRepo.findAll(pageable);

	        List<EmployeeDto> employeeDtoList = employeePage.getContent().stream()
	                .map(employee -> new EmployeeDto(
	                        employee.getEmployeeId(), 
	                        employee.getFirstName(), 
	                        employee.getLastName(), 
	                        employee.getPhoneNumber(), 
	                        employee.getEmployeeEmail(), 
	                        employee.getEmployeePosition(), 
	                        employee.getEmployeeHireDate(), 
	                        employee.getBankIfscNumber(), 
	                        employee.getEmployeeSalary(), 
	                        employee.getEmployeeStatus()))
	                .collect(Collectors.toList());

	        return new ResponseDto<>(
	                employeePage.getSize(),
	                employeePage.getTotalElements(),
	                employeePage.getTotalPages(),
	                employeeDtoList,
	                employeePage.isLast());
	    }

	 @Override
	    public ResponseDto<SalaryAccountDto> getSalaryAccountByNumber(long accountNumber) {
	        Optional<SalaryAccount> salaryAccountOpt = salaryAccountRepo.findById(accountNumber);
	        if (salaryAccountOpt.isPresent()) {
	            SalaryAccount salaryAccount = salaryAccountOpt.get();
	            SalaryAccountDto salaryAccountDto = new SalaryAccountDto(
	                    salaryAccount.getAccountNumber(),
	                    salaryAccount.getAccountHolderName());

	            return new ResponseDto<>(
	                    1, // Since we're returning a single account, size is 1
	                    1, // Total elements is 1
	                    1, // Total pages is 1
	                    List.of(salaryAccountDto),
	                    true); // Last page because there's only one item
	        } else {
	            return new ResponseDto<>(
	                    0, // Size is 0
	                    0, // No elements found
	                    0, // No pages
	                    List.of(), // Empty list
	                    true); // Last page
	        }
	    }


	@Override
	public SalaryAccount updateSalaryAccount(int employeeId, SalaryAccount salaryAccount) {
		// TODO Auto-generated method stub
		Employee employeeDb;
		Optional<Employee> employeeOptional = employeeRepo.findById(employeeId);
		if(!employeeOptional.isPresent())
			return null;
		
		employeeDb = employeeOptional.get();
		
		SalaryAccount salaryDetails = employeeDb.getSalaryAccount();
		
		salaryDetails.setAccountHolderName(salaryAccount.getAccountHolderName());
		
		employeeDb.setSalaryAccount(salaryDetails);
		
		Employee employee = employeeRepo.save(employeeDb);
		return null;
	}


	@Override
	public void deleteByAccountNumber(long accountNumber) {
		// TODO Auto-generated method stub
		Optional<Employee> employeeoptional = employeeRepo.findBySalaryAccountAccountNumber(accountNumber);
		if(!employeeoptional.isPresent())
			throw new RuntimeException("Employee with account number " + accountNumber + " not found.");
		
		Employee employee = employeeoptional.get();
		employeeRepo.delete(employee);
		
	}
}
