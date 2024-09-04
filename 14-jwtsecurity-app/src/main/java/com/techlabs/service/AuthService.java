package com.techlabs.service;

import com.techlabs.dto.LoginDto;
import com.techlabs.dto.RegistrationDto;
import com.techlabs.entity.Users;

public interface AuthService {

	Users register(RegistrationDto registrationDto);
	String login(LoginDto loginDto);
	
}
