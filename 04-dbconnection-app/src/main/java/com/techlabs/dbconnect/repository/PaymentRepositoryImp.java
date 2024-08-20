package com.techlabs.dbconnect.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlabs.dbconnect.entity.Payment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class PaymentRepositoryImp implements PaymentRepository{
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		TypedQuery<Payment> query = manager.createQuery("select p from Payment p", Payment.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void addPayments(Payment payment) {
		// TODO Auto-generated method stub
		manager.persist(payment);
	}

	@Override
	@Transactional
	public void upadtePayments(Payment payment) {
		// TODO Auto-generated method stub
		manager.merge(payment);
	}

	@Override
	@Transactional
	public void deletePayments(int paymentId) {
		// TODO Auto-generated method stub
		Payment payment = manager.find(Payment.class, paymentId);
		if(payment != null) {
			manager.remove(payment);
		}
	}

}
