package com.techlabs.mapping.entity;

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
@Table(name="address")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Address {
	
	@Column(name="addressId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	@Column(name="buildingName")
	private String buildingName;
	@Column(name="areaName")
	private String areaName;
	@Column(name="city")
	private String city;
	@Column(name="pincode")
	private int pincode;
}
