package com.techlabs.dbConnect.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dtos.LoginDto;
import com.techlabs.dbConnect.dtos.UsersDto;
import com.techlabs.dbConnect.entity.Role;
import com.techlabs.dbConnect.entity.Users;
import com.techlabs.dbConnect.exception.UserApiException;
import com.techlabs.dbConnect.repository.RoleRepo;
import com.techlabs.dbConnect.repository.UserRepository;
import com.techlabs.dbConnect.security.JwtTokenProvider;

@Service
public class AuthServiceImp implements AuthService{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Override
	public Users update(int userId, UsersDto usersDto) {
	    // Fetch the user by ID, or throw an exception if not found
	    Users users = userRepo.findById(userId).orElseThrow(() -> 
	        new UserApiException(HttpStatus.NOT_FOUND, "User not found"));

	    // Check if the provided password matches the existing user's password
//	    if (!passwordEncoder.matches(usersDto.getPassword(), users.getPassword())) {
//	        throw new UserApiException(HttpStatus.BAD_REQUEST, "Old password is incorrect");
//	    }

	    // Update the username if provided
	    users.setUserName(usersDto.getUserName());
	    users.setPassword(passwordEncoder.encode(usersDto.getPassword()));
	    System.out.println("New User Name : "+users.getUserName());
	    System.out.println("New password : "+users.getPassword());

	    // Encode and update the new password if provided
//	    if (usersDto.getPassword() != null && !usersDto.getPassword().isEmpty()) {
//	        
//	    }

	    // Save the updated user entity back to the repository
	    return userRepo.save(users);
	}



	
	@Override
	public Users register(UsersDto usersDto) {
	    // Check if the user already exists by username
	    Users existingUser = userRepo.findByUserName(usersDto.getUserName()).orElse(null);

	    if (existingUser != null) {
	        // Check if the password matches
	        if (passwordEncoder.matches(usersDto.getPassword(), existingUser.getPassword())) {
	            // Add the new role to the existing user's roles
	        	Role newRole = roleRepo.findByRoleName(usersDto.getRoles())
                        .orElseThrow(() -> new UserApiException(HttpStatus.NOT_FOUND, "Role not found"));

	            if (!existingUser.getRoles().contains(newRole)) {
	                existingUser.getRoles().add(newRole);
	                return userRepo.save(existingUser);
	            } else {
	                throw new UserApiException(HttpStatus.BAD_REQUEST, "User already has this role assigned");
	            }
	        } else {
	            throw new UserApiException(HttpStatus.BAD_REQUEST, "Password does not match");
	        }
	    }

	    // If the user does not exist, create a new user
	    Users newUser = new Users();
	    newUser.setUserName(usersDto.getUserName());
	    newUser.setPassword(passwordEncoder.encode(usersDto.getPassword()));

	    Role userRole = roleRepo.findByRoleName(usersDto.getRoles()).orElseThrow(
	        () -> new UserApiException(HttpStatus.NOT_FOUND, "Role not found"));
	    
	    List<Role> roles = new ArrayList<>();
	    roles.add(userRole);
	    newUser.setRoles(roles);
	    
	    return userRepo.save(newUser);
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
