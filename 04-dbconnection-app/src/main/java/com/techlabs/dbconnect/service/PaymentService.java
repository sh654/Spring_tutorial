package com.techlabs.dbconnect.service;

import java.util.List;

import com.techlabs.dbconnect.entity.Payment;

public interface PaymentService {

	List<Payment> getAllPayments();
	
	void addPayments(Payment payment);
	void deletePayments(int paymetId);
	void updatePayments(Payment payment);
	
}
