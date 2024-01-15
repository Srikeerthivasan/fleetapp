package com.kindsonthegenius.fleetapp.services;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kindsonthegenius.fleetapp.models.User;
import com.kindsonthegenius.fleetapp.repositories.UserRepository;

@Service
public class UserService {

	@Autowired private UserRepository userRepository;
	@Autowired private BCryptPasswordEncoder encoder;
	
	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public User findById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}
}
