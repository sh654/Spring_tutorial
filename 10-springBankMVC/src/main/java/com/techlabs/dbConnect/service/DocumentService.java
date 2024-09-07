package com.techlabs.dbConnect.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.techlabs.dbConnect.dtos.DocumentUploadDto;

public interface DocumentService {

	void verifyCustomer(int customerId);
	
	ResponseEntity<Map<String, String>> uploadImage(DocumentUploadDto uploadDto);
	
	void verifyAdmin(int adminId);
	
}
