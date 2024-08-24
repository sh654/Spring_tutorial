package com.techlabs.dbConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.dbConnect.entity.SalaryAccount;

@Repository
public interface SalaryAccountRepository extends JpaRepository<SalaryAccount, Long> {
    // You can add custom query methods here if needed
}
