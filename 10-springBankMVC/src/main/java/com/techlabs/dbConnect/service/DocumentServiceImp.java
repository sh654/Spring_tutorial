package com.techlabs.dbConnect.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.CloudinaryConfig;
import com.techlabs.dbConnect.dtos.DocumentUploadDto;
import com.techlabs.dbConnect.entity.Customers;
import com.techlabs.dbConnect.entity.Document;
import com.techlabs.dbConnect.entity.EmailDetails;
import com.techlabs.dbConnect.enums.KycStatus;
import com.techlabs.dbConnect.repository.CustomerRepository;
import com.techlabs.dbConnect.repository.DocumentRepository;

import jakarta.transaction.Transactional;


@Service
public class DocumentServiceImp implements DocumentService{

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private DocumentRepository documentRepo;
	
	@Autowired
	private CloudinaryConfig cloudConfig;
	
	@Autowired
	private CloudinaryService cloudinaryService;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	@Transactional
	public void verifyCustomer(int customerId) {
	    Customers customer = customerRepo.findById(customerId)
	            .orElseThrow(() -> new RuntimeException("Customer not found"));

	    List<Document> documents = customer.getDocuments();

	    boolean allVerified = documents.stream().allMatch(Document::isVerified);

	    if (allVerified) {
	        customer.setKycStatus(KycStatus.APPROVED);
	        EmailDetails emailDetail = sendEmail(customer);
	        emailService.sendSimpleEmail(emailDetail);
	    } else {
	        customer.setKycStatus(KycStatus.PENDING);
	        EmailDetails emailDetail = sendEmail(customer);
	        emailService.sendSimpleEmail(emailDetail);
	    }

	    customerRepo.save(customer); // Save the updated customer
	}
	
	private EmailDetails sendEmail(Customers customer) {
		EmailDetails emailDetails = new EmailDetails();
		emailDetails.setRecipient(customer.getUser().getUserName());
		emailDetails.setSubject("Kyc Approved");
		emailDetails.setMsgBody("Your Kyc Status is now : "+customer.getKycStatus()+ "\n You can now continue Banking !");
		
		return emailDetails;
	}

	@Override
	public ResponseEntity<Map<String, String>> uploadImage(DocumentUploadDto uploadDto) {
		
		try{
			if(uploadDto.getName().isEmpty() || uploadDto.getFile().isEmpty()) {
				return ResponseEntity.badRequest().body(Map.of("error", "Invalid input"));
			}
			
			
			Customers customer = customerRepo.findById(uploadDto.getCustomerId())
					.orElseThrow(() -> new RuntimeException("Customer not found"));
			
			String url = cloudinaryService.uploadFile(uploadDto.getFile(), "customer_documents");
			
			if (url == null) {
                return ResponseEntity.status(500).body(Map.of("error", "File upload failed"));
            }
			
			Document document = new Document();
			document.setName(uploadDto.getName());
			document.setUrl(url);
			document.setCustomer(customer);
			document.setVerified(false);
			
			documentRepo.save(document);
			return ResponseEntity.ok().body(Map.of("url", document.getUrl(), "message", "Document uploaded successfully"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(500).body(Map.of("error", "Internal server error"));
		}
	}

	@Override
	public void verifyAdmin(int adminId) {
		// TODO Auto-generated method stub
		
	}


}
