package com.techlabs.dbConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
