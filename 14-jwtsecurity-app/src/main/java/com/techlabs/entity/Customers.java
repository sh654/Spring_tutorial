package com.techlabs.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="customers")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Customers {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int columnId;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	
	@Column
	private String mobileNmber;
}
