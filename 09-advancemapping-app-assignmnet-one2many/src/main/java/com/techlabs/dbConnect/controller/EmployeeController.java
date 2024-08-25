package com.techlabs.dbConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbConnect.dto.EmployeeDto;
import com.techlabs.dbConnect.entity.Client;
import com.techlabs.dbConnect.entity.Employee;
import com.techlabs.dbConnect.service.EmployeeService;

@RestController
@RequestMapping("/bank")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employees")
	public ResponseEntity<Employee> addEmployees(@RequestBody EmployeeDto employeeDto){
		return ResponseEntity.ok(employeeService.addEmployees(employeeDto));
	}
	
	@PutMapping("/employees/client")
	public ResponseEntity<Employee> allocateClient(@RequestParam int employeeId,@RequestBody Client client){
		return ResponseEntity.ok(employeeService.allocateClient(employeeId, client));
	}
}
