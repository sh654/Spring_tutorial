package com.techlabs.dbConnect.entity;

import java.time.LocalDate;

import com.techlabs.dbConnect.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Salary {

    @Id
    @Column(name="salaryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salaryId;

    @Column(name="salaryMonth")
    private int salaryMonth;

    @Column(name="grossSalary")
    private double grossSalary;

    @Column(name="deductions")
    private double deductions;

    @Column(name="netSalary")
    private double netSalary;

    @Column(name="paymentDate")
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;

    // Method to calculate and set the grossSalary
    public void calculateGrossSalary() {
        this.grossSalary = this.netSalary + this.deductions;
    }

    // Setter for netSalary that automatically updates grossSalary
    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
        calculateGrossSalary();
    }

    // Setter for deductions that automatically updates grossSalary
    public void setDeductions(double deductions) {
        this.deductions = deductions;
        calculateGrossSalary();
    }
}
