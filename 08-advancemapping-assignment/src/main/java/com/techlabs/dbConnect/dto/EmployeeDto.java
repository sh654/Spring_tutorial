package com.techlabs.dbConnect.dto;

import org.springframework.web.bind.annotation.RequestMapping;

import com.techlabs.dbConnect.entity.SalaryAccount;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class EmployeeDto {

	private int employeeId;
	private String firstName;
	private String lastName;
    private String phoneNumber;
    private String employeeEmail;
    
    private String employeePosition;
   
    private String employeeHireDate;
   
    private String employeeSalary;
  
    private String bankIfscNumber;
  
    private String employeeStatus;
  

}
