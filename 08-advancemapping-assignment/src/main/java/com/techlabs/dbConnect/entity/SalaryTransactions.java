package com.techlabs.dbConnect.entity;

import java.sql.Date;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class SalaryTransactions {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int transactionId;
		
	    private Date transactionDate;
	    private double amount;
	    private Status status;
}
