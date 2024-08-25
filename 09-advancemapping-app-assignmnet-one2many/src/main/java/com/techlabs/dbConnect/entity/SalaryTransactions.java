package com.techlabs.dbConnect.entity;

import java.time.LocalDate;

import com.techlabs.dbConnect.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class SalaryTransactions {

	@Id
	@Column(name="transactionId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	
	@Column(name="transactionDate")
	private LocalDate transactionDate;
	
	@Column(name="amount")
	private double amount;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private Status status;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="salaryId")
	private Salary salaryTransaction;
	
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="accountNumber", referencedColumnName = "accountNumber")
    private SalaryAccount salaryAccount;
}
