package com.techlabs.dbConnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dtos.PageResponse;
import com.techlabs.dbConnect.dtos.UsersDto;
import com.techlabs.dbConnect.entity.Users;
import com.techlabs.dbConnect.repository.RoleRepo;
import com.techlabs.dbConnect.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{

	 @Autowired
	 private UserRepository userRepo;
	 
	 @Autowired
	 private RoleRepo roleRepo;

	@Override
	public UsersDto addUser(Users users) {
		// TODO Auto-generated method stub
		
		users = userRepo.save(users);
		return toUsersDtoMapper(users);
	}

	@Override
	public UsersDto getUserById(int userId) {
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UsersDto usersDto = new UsersDto();
        // Map Entity to DTO
        usersDto.setUserId(user.getUserId());
        usersDto.setUserName(user.getUserName());
        
//        usersDto.setRoles(user.getRoles());
        
        
        return usersDto;
    }
	@Override
	public UsersDto updateUser(int userId, UsersDto usersDto) {
		// TODO Auto-generated method stub
		Users user = new Users();
		user = userRepo.findById(userId)
				.orElseThrow(()-> new RuntimeException("User Doesn't Exsists"));
		
		user.setUserName(usersDto.getUserName());
		user.setPassword(usersDto.getPassword());
		user = userRepo.save(user);
		return toUsersDtoMapper(user);
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageResponse<UsersDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	   
	private UsersDto toUsersDtoMapper(Users users) {
		UsersDto usersDto = new UsersDto();
		usersDto.setUserId(users.getUserId());
		usersDto.setUserName(users.getUserName());
		usersDto.setPassword(users.getPassword());
		//usersDto.setRoles(users.getRoles());;
		
		usersDto.setCreatedAt(users.getCreatedAt());
		usersDto.setUpdatedAt(users.getUpdatedAt());
		return usersDto;
	}
	
	private Users toUsersMapper(UsersDto usersDto) {
		Users users = new Users();
		users.setUserName(usersDto.getUserName());
		users.setPassword(usersDto.getPassword());
		//users.setRoles(usersDto.getRoles());
		users.setCreatedAt(usersDto.getCreatedAt());
		users.setUpdatedAt(usersDto.getUpdatedAt());
		return users;
	}
	
}
