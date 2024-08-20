package com.techlabs.dbConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbConnect.entity.Employee;
import com.techlabs.dbConnect.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/employee")
	public List<Employee> getAllEmployee(){
		return empService.getAllEmployee();
		
	}
	
	@GetMapping("/employee/{empid}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int empid){
		return ResponseEntity.ok(empService.getEmployee(empid));
		
	}
	
	@PostMapping("/employee")
	public String addEmployee(@RequestBody Employee employee) {
		empService.addEmployee(employee);
		return "Record Added";
	}
	
	@DeleteMapping("/employee/{empid}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int empid){
		empService.deleteEmployee(empid);
		return ResponseEntity.ok("Record Deleted Successfully");
	}
	
	@GetMapping("employee/name")
	public ResponseEntity<List<Employee>> getEmployeeByName(@RequestParam String name){
		return ResponseEntity.ok(empService.getEmployeeByName(name));
	}
	
	
}
