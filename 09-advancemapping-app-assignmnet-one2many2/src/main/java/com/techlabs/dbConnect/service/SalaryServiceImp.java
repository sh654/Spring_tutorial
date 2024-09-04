package com.techlabs.dbConnect.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dto.SalaryDto;
import com.techlabs.dbConnect.entity.Employee;
import com.techlabs.dbConnect.entity.Salary;
import com.techlabs.dbConnect.entity.SalaryTransactions;
import com.techlabs.dbConnect.repository.EmployeeRepo;
import com.techlabs.dbConnect.repository.SalaryRepository;
import com.techlabs.dbConnect.repository.SalaryTransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class SalaryServiceImp implements SalaryService{

	@Autowired
    private EmployeeRepo employeeRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private SalaryTransactionRepository salaryTransactionsRepository;

    @Transactional
    public Salary createSalaryForEmployee(int employeeId, double deductions, SalaryDto salaryDto) {
    	
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if(!optionalEmployee.isPresent())
        	return null;

        Employee employee = optionalEmployee.get();
        
        // Create a new Salary object
        Salary salary = new Salary();
        salary.setNetSalary(employee.getEmployeeSalary()); // Set net salary from Employee
        salary.setDeductions(deductions); // Set deductions
        salary.calculateGrossSalary(); // Calculate gross salary
        salary.setPaymentDate(salaryDto.getPaymentDate());
        salary.setSalaryMonth(salaryDto.getSalaryMonth());
        salary.setStatus(salaryDto.getStatus());

        // Save the Salary object
        salaryRepository.save(salary);

        // Create a SalaryTransaction object
        SalaryTransactions transaction = new SalaryTransactions();
        transaction.setSalaryTransaction(salary);
        transaction.setSalaryAccount(employee.getSalaryAccount());
        transaction.setTransactionDate(LocalDate.now());
        transaction.setAmount(salary.getNetSalary());
        transaction.setStatus(salary.getStatus());

        // Save the SalaryTransaction
        salaryTransactionsRepository.save(transaction);

        return salary;
    }

    @Transactional
    public Salary updateSalaryAndTransaction(int salaryId, double newDeductions) {
        Salary salary = salaryRepository.findById(salaryId)
                .orElseThrow(() -> new RuntimeException("Salary not found"));

        // Update deductions and recalculate gross salary
        salary.setDeductions(newDeductions);
        salary.calculateGrossSalary();

        // Update the SalaryTransaction
        SalaryTransactions transaction = salaryTransactionsRepository.findBySalaryTransaction(salary)
                .orElseThrow(() -> new RuntimeException("Transaction not found for the given salary"));
        transaction.setAmount(salary.getNetSalary());

        // Save updated salary and transaction
        salaryRepository.save(salary);
        salaryTransactionsRepository.save(transaction);

        return salary;
    }
	
}
