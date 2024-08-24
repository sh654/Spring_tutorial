package com.techlabs.dbConnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.dbConnect.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

//	Page<Client> findClientByName(String companyName, Pageable pageable);
	Page<Client> findClientByCompanyName(String companyName, Pageable pageable);
}
