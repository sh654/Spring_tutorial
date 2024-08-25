package com.techlabs.dbConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Client;

public interface ClientRepo extends JpaRepository<Client, Integer>{

	
	
}
