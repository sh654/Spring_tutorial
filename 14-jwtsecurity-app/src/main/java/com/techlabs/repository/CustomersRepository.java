package com.techlabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.entity.Customers;

public interface CustomersRepository extends JpaRepository<Customers, Integer>{

}
