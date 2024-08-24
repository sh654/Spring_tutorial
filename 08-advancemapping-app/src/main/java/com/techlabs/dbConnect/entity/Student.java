package com.techlabs.dbConnect.entity;

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
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="students")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Student {

	@Id
	@Column(name="rollnumber")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rollNumber;
	
	@Column(name="name")
	private String studentName;
	
	@Column(name="age")
	private int sAge;
	
	@OneToOne(cascade = CascadeType.ALL)
//	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="addressid")
	private Address studentAddress;
	
}
