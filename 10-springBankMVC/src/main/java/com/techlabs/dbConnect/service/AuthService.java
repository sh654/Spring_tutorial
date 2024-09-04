package com.techlabs.dbConnect.service;

import com.techlabs.dbConnect.dtos.LoginDto;
import com.techlabs.dbConnect.dtos.UsersDto;
import com.techlabs.dbConnect.entity.Users;

public interface AuthService {
	Users register(UsersDto usersDto);
    String login(LoginDto loginDto);
    Users update(int userId, UsersDto usersDto);
}
