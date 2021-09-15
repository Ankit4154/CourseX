package io.coursex.springbootstarter.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.coursex.springbootstarter.Utils;
import io.coursex.springbootstarter.model.User;
import io.coursex.springbootstarter.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	Map<String, User> users;
	Utils util;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	UserRepository userRepository;

	public UserServiceImpl() {
	}
	
	@Autowired
	public UserServiceImpl(Utils util, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.util = util;
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public User addUser(User user) {
		// Automatic constraints validation on User properties via Valid annotation
		String userId = util.generateUserId();
		user.setUserId(userId);
		user.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		if (users == null) {
			users = new HashMap<>();
		}
		
		users.put(userId, user);
		
		// Mapping source objects to destination objects via modelmapper
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User userEntity = modelMapper.map(user, User.class);
		userRepository.save(userEntity);
		
		return userEntity;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}else {
			return new org.springframework.security.core.userdetails.User(user.getEmail(), 
					user.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
		}
	}
}
