package com.techlabs.dbConnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="employee")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Employee {

	@Id
	@Column(name="employeeid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="phonenumber")
	private String phoneNumber;
	
	@Column(name="email")
	private String employeeEmail;
	
	@Column(name="position")
	private String employeePosition;
	
	@Column(name="hiredate")
	private String employeeHireDate;
	
	@Column(name="salary")
	private String employeeSalary;
	
	@Column(name="bankaccountnumber")
	private long bankAccountNumber;
	
	@Column(name="bankifscnumber")
	private String bankIfscNumber;
	
	@Column(name="status")
	private String employeeStatus;
	
	
}
