package com.techlabs.dbConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techlabs.dbConnect.dto.SalaryDto;
import com.techlabs.dbConnect.entity.Salary;
import com.techlabs.dbConnect.entity.SalaryAccount;
import com.techlabs.dbConnect.entity.SalaryTransactions;
import com.techlabs.dbConnect.service.SalaryAccountService;
import com.techlabs.dbConnect.service.SalaryService;


@RestController
@RequestMapping("/bankapp")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;
    
    @Autowired
    private SalaryAccountService salaryAccountService;

    @PostMapping("/salary")
    public ResponseEntity<Salary> createSalary(@RequestParam int employeeId, @RequestParam double deductions ,@RequestBody SalaryDto salaryDto) {
        Salary salary = salaryService.createSalaryForEmployee(employeeId, deductions, salaryDto);
        return ResponseEntity.ok(salary);
    }

    @PutMapping("/salaryupdate")
    public ResponseEntity<Salary> updateSalary(@RequestParam int salaryId, @RequestParam double deductions) {
        Salary updatedSalary = salaryService.updateSalaryAndTransaction(salaryId, deductions);
        return ResponseEntity.ok(updatedSalary);
    }
    
    @PutMapping("/salary/transaction")
    public ResponseEntity<SalaryAccount> allocateTransaction(@RequestParam long accountNumber, @RequestBody List<SalaryTransactions> transactions) {
        SalaryAccount updatedSalaryAccount = salaryAccountService.allocateTransactions(accountNumber, transactions);
        if (updatedSalaryAccount == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedSalaryAccount);
    }

}

