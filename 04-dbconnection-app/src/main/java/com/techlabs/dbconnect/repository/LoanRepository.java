package com.techlabs.dbconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbconnect.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer>{

}
