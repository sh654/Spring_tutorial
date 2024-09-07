package com.techlabs.dbConnect.service;

public interface AdminService {

	String verifyCustomerDocument(int documentId, int customerId);
	
	String verifyAdminDoument(int documentId, int adminId);
}
