package com.techlabs.dbConnect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Admin;
import com.techlabs.dbConnect.entity.Customers;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	Optional<Admin> findByUser_UserName(String userName);
	
}
