package com.techlabs.dbConnect.dto;

import java.util.Date;

import com.techlabs.dbConnect.entity.SalaryAccount;
import com.techlabs.dbConnect.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDto {

	private int employeeId;
    
    private String firstName;
  
    private String lastName;
    
    private String phoneNumber;
    
    private String employeeEmail;
    
    private String employeePosition;
    
    private Date employeeHireDate;

    private double employeeSalary;
    
    private Status employeeStatus;
    
    private SalaryAccount salaryAccount;
}
