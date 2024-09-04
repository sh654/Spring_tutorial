package com.techlabs.dbConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
