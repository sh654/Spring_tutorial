package com.techlabs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.entity.Users;

public interface UserRepo extends JpaRepository<Users, Integer>{

	Optional<Users>  findByUserName(String userName);
	
	boolean existsByUserName(String userName);
	
}
