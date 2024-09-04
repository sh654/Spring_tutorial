package com.techlabs.dbConnect.service;

import com.techlabs.dbConnect.dtos.PageResponse;
import com.techlabs.dbConnect.dtos.UsersDto;
import com.techlabs.dbConnect.entity.Users;

public interface UserService {

	 UsersDto addUser(Users users);
	 UsersDto getUserById(int userId);
	 UsersDto updateUser(int userId, UsersDto usersDto);
	 void deleteUser(int userId);
	 PageResponse<UsersDto> getAllUsers();
	
}
