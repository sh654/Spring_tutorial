package com.techlabs.dbConnect.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

	Optional<Employee> findBySalaryAccountAccountNumber(long accountNumber);
	
}
