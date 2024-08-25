package com.techlabs.dbConnect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Salary;
import com.techlabs.dbConnect.entity.SalaryAccount;
import com.techlabs.dbConnect.entity.SalaryTransactions;

public interface SalaryTransactionRepository extends JpaRepository<SalaryTransactions, Integer>{
	Optional<SalaryTransactions> findBySalaryTransaction(Salary salary);
	Optional<SalaryTransactions> findByTransactionId(Integer id);

}
