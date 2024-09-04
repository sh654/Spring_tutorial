package com.techlabs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlabs.dto.LoginDto;
import com.techlabs.dto.RegistrationDto;
import com.techlabs.entity.Role;
import com.techlabs.entity.Users;
import com.techlabs.exception.UserApiException;
import com.techlabs.repository.RoleRepo;
import com.techlabs.repository.UserRepo;
import com.techlabs.security.JwtTokenProvider;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider tokenProvider;

	@Override
	public Users register(RegistrationDto registrationDto) {
		// TODO Auto-generated method stub
		if(userRepo.existsByUserName(registrationDto.getUserName()))
			throw new UserApiException(HttpStatus.BAD_REQUEST, "User already exists");
		
		Users user = new Users();
		user.setUserName(registrationDto.getUserName());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		List<Role> roles = new ArrayList<>();
		
		Role userRole = roleRepo.findByRoleName(registrationDto.getRoleName()).get();
		roles.add(userRole);
		user.setRoles(roles);
		return userRepo.save(user);
	}

	@Override
	public String login(LoginDto loginDto) {
		// TODO Auto-generated method stub
		
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = tokenProvider.generateToken(authentication);
			
			return token;
		}catch(BadCredentialsException e) {
			throw new UserApiException(HttpStatus.NOT_FOUND, "username or password is incorrect");
		}
		
	}
	
	
	
}
