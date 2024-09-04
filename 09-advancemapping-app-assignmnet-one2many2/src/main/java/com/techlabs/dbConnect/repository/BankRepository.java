package com.techlabs.dbConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>{

}
