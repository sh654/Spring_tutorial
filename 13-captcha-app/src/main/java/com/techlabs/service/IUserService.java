package com.techlabs.service;

import java.util.List;
import java.util.Optional;

import com.techlabs.entity.Users;


public interface IUserService {

	void createUser(Users user);
	List<Users> getAllUsers();
	Optional<Users> getOneUser(Integer Id);
}
