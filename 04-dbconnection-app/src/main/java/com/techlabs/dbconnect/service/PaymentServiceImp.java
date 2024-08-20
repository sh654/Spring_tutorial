package com.techlabs.dbconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbconnect.entity.Payment;
import com.techlabs.dbconnect.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentServiceImp implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepo;
	
	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		return paymentRepo.getAllPayments();
	}

	@Override
	@Transactional
	public void addPayments(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepo.addPayments(payment);
	}

	@Override
	@Transactional
	public void deletePayments(int paymetId) {
		// TODO Auto-generated method stub
		paymentRepo.deletePayments(paymetId);
	}

	@Override
	public void updatePayments(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepo.upadtePayments(payment);
	}

}
