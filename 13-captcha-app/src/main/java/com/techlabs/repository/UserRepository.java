package com.techlabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

}
