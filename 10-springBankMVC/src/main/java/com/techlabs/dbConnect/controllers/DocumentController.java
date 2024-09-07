package com.techlabs.dbConnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.techlabs.dbConnect.dtos.DocumentUploadDto;
import com.techlabs.dbConnect.service.DocumentService;

import java.util.Map;

@RestController
@RequestMapping("/bank")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadDocument(
        @RequestParam("name") String name, 
        @RequestParam("file") MultipartFile file,
        @RequestParam("customerId") int customerId) {

        DocumentUploadDto documentDto = new DocumentUploadDto();
        documentDto.setName(name);
        documentDto.setFile(file);
        documentDto.setCustomerId(customerId);

        return documentService.uploadImage(documentDto);
    }
    
    @PostMapping("/customer-verify")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> verifyCustomer(@RequestParam int customerId){
    	documentService.verifyCustomer(customerId);
    	return ResponseEntity.ok("Customer is Now Approved");
    }
}

