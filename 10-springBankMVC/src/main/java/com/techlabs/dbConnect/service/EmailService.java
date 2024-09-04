package com.techlabs.dbConnect.service;

import com.techlabs.dbConnect.entity.EmailDetails;

public interface EmailService {

	String sendSimpleEmail(EmailDetails deatils);
	
	String sendMailWithAttachments(EmailDetails details);
	
}
