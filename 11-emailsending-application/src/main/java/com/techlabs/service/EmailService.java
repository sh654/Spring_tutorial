package com.techlabs.service;

import com.techlabs.entity.EmailDetails;

public interface EmailService {

	String sendSimpleEmail(EmailDetails deatils);
	
	String sendMailWithAttachments(EmailDetails details);
	
}
