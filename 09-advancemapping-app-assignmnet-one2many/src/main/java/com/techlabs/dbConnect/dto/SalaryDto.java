package com.techlabs.dbConnect.dto;

import java.time.LocalDate;

import com.techlabs.dbConnect.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SalaryDto {

	private int salaryId;

    private int salaryMonth;

    private double grossSalary;

    private double deductions;

    private double netSalary;

    private LocalDate paymentDate;

    private Status status;
}
