package com.techlabs.dbConnect.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Integer>{
	
	Page<Transactions> findBySenderAccountNumber(Long senderAccountNumber, Pageable pageable);
	Optional<Transactions> findByReceiverAccountNumber(Long receiverAccountNumber);
	Page<Transactions> findBySenderAccountNumberOrReceiverAccountNumber(Long senderAccountNumber,
			Long senderAccountNumber2, Pageable pageable);
	
}
