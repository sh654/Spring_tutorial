package com.techlabs.dbConnect.entity;

import java.util.Random;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="employee")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Employee {

    @Id
    @Column(name="employeeid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    
    @Column(name="firstname")
    private String firstName;
    
    @Column(name="lastname")
    private String lastName;
    
    @Column(name="phonenumber")
    private String phoneNumber;
    
    @Column(name="email")
    private String employeeEmail;
    
    @Column(name="position")
    private String employeePosition;
    
    @Column(name="hiredate")
    private String employeeHireDate;
    
    @Column(name="salary")
    private String employeeSalary;
    
    @Column(name="bankifscnumber")
    private String bankIfscNumber;
    
    @Column(name="status")
    private String employeeStatus;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="accountnumber", referencedColumnName = "accountNumber")
    private SalaryAccount salaryAccount;

    @PrePersist
    protected void onCreate() {
        // Generate account number and assign to SalaryAccount
        long accountNumber = generateAccountNumber();
        this.salaryAccount.setAccountNumber(accountNumber);

        // Assign the same IFSC code to all employees
        this.bankIfscNumber = generateIfscNumber();
    }

    private long generateAccountNumber() {
        Random random = new Random();
        return 1000000000L + random.nextLong(9000000000L); // Generates a 10-digit random number
    }

    private String generateIfscNumber() {
        // Static IFSC code for all employees
        return "IFSC1234";
    }
}
