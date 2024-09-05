package com.techlabs.dbConnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.entity.EmailDetails;


public interface EmailService {
	
	

	String sendSimpleEmail(EmailDetails deatils);
	
	String sendMailWithAttachments(EmailDetails details);
	
}
