package com.techlabs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlabs.dtos.LoginDto;
import com.techlabs.dtos.RegistrationDto;
import com.techlabs.entity.Users;
import com.techlabs.repository.RoleRepo;
import com.techlabs.repository.UserRepo;
import com.techlabs.security.JwtTokenProvider;
import com.techlabs.entity.*;
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
		if(userRepo.existsByUserName(registrationDto.getUserName()))
			throw new RuntimeException("User already exists");
		
		Users user = new Users();
		user.setUserName(registrationDto.getUserName());
		user.setPassword(registrationDto.getPassword());
		List<Roles> roles = new ArrayList<>();
		
		Roles userRole = roleRepo.findByRoleName(registrationDto.getRoleName()).get();
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
			throw new RuntimeException( "username or password is incorrect");
		}

	}
}
