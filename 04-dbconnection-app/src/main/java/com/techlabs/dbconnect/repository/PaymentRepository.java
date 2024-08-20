package com.techlabs.dbconnect.repository;

import java.util.List;

import com.techlabs.dbconnect.entity.Payment;

public interface PaymentRepository {

	List<Payment> getAllPayments();
	void addPayments(Payment payment);
	void upadtePayments(Payment payment);
	void deletePayments(int paymentId);
}
