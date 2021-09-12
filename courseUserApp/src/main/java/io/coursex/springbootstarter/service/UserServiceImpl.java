package io.coursex.springbootstarter.service;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.coursex.springbootstarter.Utils;
import io.coursex.springbootstarter.model.User;
import io.coursex.springbootstarter.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	Map<String, User> users;
	Utils util;
	
	UserRepository userRepository;

	public UserServiceImpl() {
	}
	
	@Autowired
	public UserServiceImpl(Utils util, UserRepository userRepository) {
		this.util = util;
		this.userRepository = userRepository;
	}

	@Override
	public User addUser(User user) {
		// Automatic constraints validation on User properties via Valid annotation
		String userId = util.generateUserId();
		user.setUserId(userId);
		if (users == null) {
			users = new HashMap<>();
		}
		users.put(userId, user);
		
		// Mapping source objects to destination objects via modelmapper
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User userEntity = modelMapper.map(user, User.class);
		user.setEncryptedPassword("testing");
		userRepository.save(userEntity);
		
		return userEntity;
	}
}
