package com.techlabs.dbConnect.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techlabs.dbConnect.enums.KycStatus;
import com.techlabs.dbConnect.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="clients")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Client {

	@Id
	@Column(name="clientId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientId;
	
	@Column(name="companyName")
	private String companyName;
	
	@Column(name="registrationNumber")
	private String registationNumber;
	
	@Column(name="contactPerson")
	private String contactPerson;
	
	@Column(name="contactEmail")
	private String contactEmail;
	
	@Column(name="contactNumber")
	private String contactNumber;
	
	@Column(name="adddress")
	private String address;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private Status status;
	
	@Column(name="creationDate")
	private LocalDate creationDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name="kycStatus")
	private KycStatus kycStatus;
	
	
	@OneToMany(mappedBy = "client", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JsonIgnore
	List<Employee> employee;
	
	
	 @PrePersist
	 protected void onCreate() {
	    this.creationDate = LocalDate.now();
	 }

}
