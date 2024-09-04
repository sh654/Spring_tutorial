package com.techlabs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

	Optional<Role> findByRoleName(String roleName);
}
