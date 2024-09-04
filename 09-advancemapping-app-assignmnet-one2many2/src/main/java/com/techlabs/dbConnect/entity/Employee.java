package com.techlabs.dbConnect.entity;

import java.util.Date;
import java.util.Random;

import com.techlabs.dbConnect.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Column(name="employeeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    
    @Column(name="firstName")
    private String firstName;
    
    @Column(name="lastName")
    private String lastName;
    
    @Column(name="phoneNumber")
    private String phoneNumber;
    
    @Column(name="email")
    private String employeeEmail;
    
    @Column(name="position")
    private String employeePosition;
    
    @Column(name="hiredate")
    private Date employeeHireDate;
    
    @Column(name="salary")
    private double employeeSalary;
    
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status employeeStatus;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="accountNumber", referencedColumnName = "accountNumber")
    private SalaryAccount salaryAccount;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name="clientId")
    private Client client;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="netSalary")
    private Salary salary;

    @PrePersist
    protected void onCreate() {
        if (this.salaryAccount == null) {
            this.salaryAccount = new SalaryAccount();
        }
        this.salaryAccount.setAccountNumber(generateAccountNumber());
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
