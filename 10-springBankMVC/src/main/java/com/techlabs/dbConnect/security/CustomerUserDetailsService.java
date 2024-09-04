package com.techlabs.dbConnect.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.entity.Users;
import com.techlabs.dbConnect.repository.UserRepository;

@Component
public class CustomerUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not present"));
		
		Set<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map((role)-> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());
		return new User(user.getUserName(),
				user.getPassword(),
				authorities);
	}
	
}