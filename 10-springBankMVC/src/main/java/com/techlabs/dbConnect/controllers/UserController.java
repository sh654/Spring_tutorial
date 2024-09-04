package com.techlabs.dbConnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbConnect.dtos.UsersDto;
import com.techlabs.dbConnect.entity.Users;
import com.techlabs.dbConnect.service.UserService;

@RestController
@RequestMapping("/bank")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UsersDto> addUsers(@RequestBody Users users){
		UsersDto usersDto = userService.addUser(users);
		return ResponseEntity.ok(usersDto);
	} 
	
	 
	@PutMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UsersDto> updateUsers(@RequestParam int userId ,@RequestBody UsersDto usersDto){
		UsersDto userDto = userService.updateUser(userId, usersDto);
		return ResponseEntity.ok(userDto);
	}
	
}
