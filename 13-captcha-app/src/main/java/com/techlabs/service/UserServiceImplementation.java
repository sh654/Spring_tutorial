package com.techlabs.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.entity.Users;
import com.techlabs.repository.UserRepository;

@Service
public class UserServiceImplementation implements IUserService {
   
	@Autowired
	private UserRepository repo;
    
	@Override
	public void createUser(Users user) {

		repo.save(user);
	}

	@Override
	public List<Users> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public Optional<Users> getOneUser(Integer id) {
		return repo.findById(id);
	}

}
