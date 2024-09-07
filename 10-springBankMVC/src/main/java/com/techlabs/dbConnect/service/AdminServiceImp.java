package com.techlabs.dbConnect.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.entity.Customers;
import com.techlabs.dbConnect.entity.Document;
import com.techlabs.dbConnect.entity.EmailDetails;
import com.techlabs.dbConnect.entity.Users;
import com.techlabs.dbConnect.repository.CustomerRepository;
import com.techlabs.dbConnect.repository.DocumentRepository;
import com.techlabs.dbConnect.repository.UserRepository;

@Service
public class AdminServiceImp implements AdminService{

	@Autowired
	private DocumentRepository documentRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private EmailService emailService;
	
	public String verifyCustomerDocument(int documentId, int customerId) {
		Document documentOpt = documentRepo.findById(documentId)
				.orElseThrow(()-> new RuntimeException("No document present of id: "+documentId));
		
		documentOpt.setVerified(true);
		documentRepo.save(documentOpt);
		
		Customers customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		
		EmailDetails emailDetails = new EmailDetails();
		emailDetails.setRecipient(customer.getUser().getUserName());
		emailDetails.setSubject("Your Document is verified : "+documentOpt.getName());
		emailDetails.setMsgBody("Dear Customer,\n\nYour document '" + documentOpt.getName() + "' has been verified successfully.\n\nThank you.");
		
		emailService.sendSimpleEmail(emailDetails);
		return null;
	}



	@Override
	public String verifyAdminDoument(int documentId, int adminId) {
		// TODO Auto-generated method stub
		return null;
	}
}
