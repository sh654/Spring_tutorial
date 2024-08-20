package com.techlabs.dbconnect.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlabs.dbconnect.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CustomerRepositoryImplement implements CustomerRepository{

	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		TypedQuery<Customer> query = manager.createQuery("select c from Customer c", Customer.class);
		return query.getResultList();
	}

	@Override
	public Customer getCustomer(int customerid) {
		
		return manager.find(Customer.class, customerid);
	}

	@Override
	@Transactional
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		manager.persist(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(int customerid) {
		// TODO Auto-generated method stub
		Customer customer = manager.find(Customer.class, customerid);
		if(customer != null) {
			manager.remove(customer);
		}
	}

	@Override
	@Transactional
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		manager.merge(customer);
	}

	
}
