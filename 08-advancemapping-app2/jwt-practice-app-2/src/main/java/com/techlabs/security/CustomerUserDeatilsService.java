package com.techlabs.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techlabs.entity.Users;
import com.techlabs.repository.UserRepo;

@Service
public class CustomerUserDeatilsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user = userRepo.findByUserName(username).orElseThrow(
				() -> new RuntimeException("404 UserName Not Found"));
		Set<SimpleGrantedAuthority> authorities = user.getRoles().stream().map((role)-> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());		
		
		
		return new User(user.getUserName(),
				user.getPassword(),
				authorities);
	}

}
