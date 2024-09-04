package com.techlabs.dbConnect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.dbConnect.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{
	
	Optional<Users> findByUserName(String userName);
	
	boolean existsByUserName(String userName);
	
}
