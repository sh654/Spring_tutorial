package com.techlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.entity.EmailDetails;
import com.techlabs.service.EmailService;

@RestController
@RequestMapping("/emailapp")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sendemail")
	public ResponseEntity<String> sendEmail(@RequestBody EmailDetails details){
		String status = emailService.sendSimpleEmail(details);
		return ResponseEntity.ok(status);
	}
	
	@PostMapping("/sendmailwithattachments")
	public ResponseEntity<String> sendMailWithAttachments(@RequestBody EmailDetails details){
		String status = emailService.sendMailWithAttachments(details);
		return ResponseEntity.ok(status);
	}
}
