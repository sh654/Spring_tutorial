package com.techlabs.dbConnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    @Column(name="accountnumber")
    private long accountNumber; // Will be set in Employee's @PrePersist method
    
    @Column(name="accountholdername")
    private String accountHolderName;
}
