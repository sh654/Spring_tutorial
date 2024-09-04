package com.techlabs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.entity.Roles;


public interface RoleRepo extends JpaRepository<Roles, Integer>{

	Optional<Roles> findByRoleName(String roleName);
	
}
