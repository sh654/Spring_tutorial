package com.techlabs.service;

import com.techlabs.dtos.LoginDto;
import com.techlabs.dtos.RegistrationDto;
import com.techlabs.entity.Users;

public interface AuthService {

	Users register(RegistrationDto registrationDto);
	String login(LoginDto loginDto);
	
}
