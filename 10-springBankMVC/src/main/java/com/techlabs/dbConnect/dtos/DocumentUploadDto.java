package com.techlabs.dbConnect.dtos;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class DocumentUploadDto {

	 private String name;
	 private MultipartFile file;
	 private int customerId; 
	
}
