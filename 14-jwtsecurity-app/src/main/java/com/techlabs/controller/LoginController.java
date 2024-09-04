package com.techlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dto.JwtAuthResponse;
import com.techlabs.dto.LoginDto;
import com.techlabs.dto.RegistrationDto;
import com.techlabs.entity.Users;
import com.techlabs.service.AuthService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<Users> register(@RequestBody RegistrationDto registrationdto){
		return ResponseEntity.ok(authService.register(registrationdto));
	}
	
	@PostMapping("/login")
		public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
			String token = authService.login(loginDto);
			JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
			jwtAuthResponse.setAccessToken(token);
			
			return ResponseEntity.ok(jwtAuthResponse);
		}
	}

