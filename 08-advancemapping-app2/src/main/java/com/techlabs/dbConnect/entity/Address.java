package com.techlabs.dbConnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="address")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Address {

	@Id
	@Column(name="addressid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	
	@Column(name="buildingname")
	private String buildingName;
	
	@Column(name="city")
	private String cityName;
	
	@Column(name="pincode")
	private int pinCode;
}
