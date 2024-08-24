package com.techlabs.dbConnect.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Page<Employee> findByFirstName(String firstName, Pageable pageable);
	Optional<Employee> findBySalaryAccountAccountNumber(long accountNumber);
}
