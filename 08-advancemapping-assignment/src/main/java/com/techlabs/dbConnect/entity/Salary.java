package com.techlabs.dbConnect.entity;

import java.sql.Date;

import com.techlabs.dbConnect.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="salary")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Salary {

	@Id
	@Column(name="salary_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int salaryId;
	
	@Column(name="salary_month")
	private int salaryMonth;
	
	@Column(name="gross_salary")
	private double grossSalary;
	
	@Column(name="deductions")
	private double salDeductions;
	
	@Column(name="net_salary")
	private double netSalary;
	
	@Column(name="payment_date")
	private Date paymentDate;
	
	@Column(name="status")
	private Status status;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="transactionId", referencedColumnName = "transactionId")
	private SalaryTransactions salaryTransactions;
	
}
