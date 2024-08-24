package com.techlabs.dbConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbConnect.dto.ResponseDto;
import com.techlabs.dbConnect.dto.SalaryAccountDto;
import com.techlabs.dbConnect.dto.EmployeeDto;
import com.techlabs.dbConnect.entity.Employee;
import com.techlabs.dbConnect.entity.SalaryAccount;
import com.techlabs.dbConnect.service.EmployeeService;


@RequestMapping("/employeeapp")
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees")
	public ResponseEntity<ResponseDto<Employee>> getAllEmployees(
			@RequestParam(required = false) String firstName,@RequestParam int pageNo,@RequestParam int pageSize)
	{
		return ResponseEntity.ok(employeeService.getAllEmployees(firstName, pageNo, pageSize));
	}
	
	@GetMapping("/employee")
    public ResponseEntity<ResponseDto<?>> getEmployeesOrSalaryAccount(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) Long accountNumber,
            @RequestParam int pageNo,
            @RequestParam int pageSize) {

        if (accountNumber != null) {
            ResponseDto<SalaryAccountDto> salaryAccountResponse = employeeService.getSalaryAccountByNumber(accountNumber);
            return ResponseEntity.ok(salaryAccountResponse);
        } else {
            ResponseDto<EmployeeDto> employeeResponse = employeeService.getAllEmployeesDto(firstName, pageNo, pageSize);
            return ResponseEntity.ok(employeeResponse);
        }
    }
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> addEmployeeDetails(@RequestBody Employee employee){
		return ResponseEntity.ok(employeeService.addStudentDetails(employee));
	}
	
	@PutMapping("/employees/{employeeId}")
	public ResponseEntity<SalaryAccount> updateEmployeeDetails(
			@PathVariable int employeeId,
			@RequestBody SalaryAccount salaryAccount){
		
		SalaryAccount salaryAcc = employeeService.updateSalaryAccount(employeeId, salaryAccount);
		if(salaryAcc == null)
			return ResponseEntity.ok(null);
		
		return ResponseEntity.ok(salaryAcc);
	}
	
	
	@DeleteMapping("/employees")
	public ResponseEntity<String> deleteRecord(
			@RequestParam long accountNumber){
		employeeService.deleteByAccountNumber(accountNumber);
		return ResponseEntity.ok("deleted");
	}
}
