package com.techlabs.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.techlabs.entity.EmailDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImplementation implements EmailService{

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}") 
	private String sender; // to fetch from field specified in properties
	
	@Override
	public String sendSimpleEmail(EmailDetails details) {
		// TODO Auto-generated method stub
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setFrom(sender);
		mailMessage.setTo(details.getRecipient());
		mailMessage.setText(details.getMsgBody());
		mailMessage.setSubject(details.getSubject());
		
		javaMailSender.send(mailMessage);
		return "Mail sent Successfully....";
	}

	@Override
	public String sendMailWithAttachments(EmailDetails details) {
		// TODO Auto-generated method stub
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		
		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(details.getRecipient());
			mimeMessageHelper.setText(details.getMsgBody());
			mimeMessageHelper.setSubject(details.getSubject());
			
			FileSystemResource file = new FileSystemResource( new File(details.getAttachment()));
			
			mimeMessageHelper.addAttachment(file.getFilename(), file);
			
			javaMailSender.send(mimeMessage);
			return "Mail Sent Successfully";
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Error While Sending";
	}


}
