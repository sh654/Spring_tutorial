package com.techlabs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name ="Employees")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Employee {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	
	@Column
	private String name;
	
	@Column
	private double Salary;
	
}
