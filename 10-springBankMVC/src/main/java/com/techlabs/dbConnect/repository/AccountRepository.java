package com.techlabs.dbConnect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.dtos.AccountsDto;
import com.techlabs.dbConnect.entity.Accounts;
import com.techlabs.dbConnect.entity.Customers;

public interface AccountRepository extends JpaRepository<Accounts, Integer>{

	Optional<Accounts> findByAccountNumber(long accountNumber);
	Optional<AccountsDto> findByCustomer(Customers customer);
}
