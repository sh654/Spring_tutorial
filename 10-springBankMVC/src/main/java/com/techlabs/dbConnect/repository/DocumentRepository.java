package com.techlabs.dbConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer>{

	
	
}
