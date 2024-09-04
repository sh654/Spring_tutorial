package com.techlabs.dbConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
}
