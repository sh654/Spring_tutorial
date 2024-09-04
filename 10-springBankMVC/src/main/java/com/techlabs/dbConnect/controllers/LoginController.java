package com.techlabs.dbConnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbConnect.dtos.JwtAuthenticateResponse;
import com.techlabs.dbConnect.dtos.LoginDto;
import com.techlabs.dbConnect.dtos.UsersDto;
import com.techlabs.dbConnect.entity.Users;
import com.techlabs.dbConnect.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/bank")
public class LoginController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<Users> postMethodName(@Valid @RequestBody UsersDto usersDto) {
		
		return ResponseEntity.ok(authService.register(usersDto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticateResponse> login(@RequestBody LoginDto loginDto){
		String token = authService.login(loginDto);
		JwtAuthenticateResponse jwtAuthResponse = new JwtAuthenticateResponse();
		jwtAuthResponse.setAccessToken(token);
		
		return ResponseEntity.ok(jwtAuthResponse);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Users> updateMethod(@Valid @RequestParam int userId, @RequestBody UsersDto userDto){
		Users updatedUser = authService.update(userId, userDto);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
}


