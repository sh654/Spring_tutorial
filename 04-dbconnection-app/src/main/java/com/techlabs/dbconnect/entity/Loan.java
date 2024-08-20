package com.techlabs.dbconnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="loan")
public class Loan {

	@Id
	@Column(name="loanid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	
	
}
