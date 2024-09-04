package com.techlabs.dbConnect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Integer>{
	
	// Method to find Customer by the username of the associated User
	Optional<Customers> findByUser_UserName(String userName);

}
