package com.techlabs.dbConnect.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techlabs.dbConnect.enums.BankName;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Bank {

	@Id
	@Column(name="bankId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="bankName")
	private BankName bankName;
	
	@Column(name="ifscNumber")
	private String ifscNumber;
	
	@OneToMany(mappedBy = "bank", cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}) // mapping instructor to know which instructor has how many course
	@JsonIgnore
	List<SalaryAccount> salaryAccount;
	 
	@jakarta.persistence.PrePersist
    protected void onCreate() {
        this.ifscNumber = generateIfscNumber();
    }
	 
	private String generateIfscNumber() {
        switch (this.bankName) {
            case SBI:
                return "SBIN0001234";
            case ICICI:
                return "ICIC0005678";
            case HDFC:
                return "HDFC0009012";
            case AXIS:
                return "AXIS0003456";
            default:
                throw new IllegalArgumentException("Invalid bank name: " + this.bankName);
        }
    }
}
