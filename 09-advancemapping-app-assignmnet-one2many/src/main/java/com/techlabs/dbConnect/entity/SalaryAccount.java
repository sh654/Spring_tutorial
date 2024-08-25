package com.techlabs.dbConnect.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="salaryaccount")
public class SalaryAccount {

    @Id
    @Column(name="accountNumber")
    private long accountNumber; // Will be set in Employee's @PrePersist method
    
    @Column(name="accountHoldername")
    private String accountHolderName;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="bankId") 
    private Bank bank;
    
    @OneToMany(mappedBy = "salaryAccount", cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}) // mapping instructor to know which instructor has how many course
	@JsonIgnore
    List<SalaryTransactions> salaryTransactions;
}
