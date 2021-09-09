package io.coursex.springbootstarter.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.coursex.springbootstarter.Utils;
import io.coursex.springbootstarter.model.User;

@Service
public class UserServiceImpl implements UserService {

	Map<String, User> users;
	Utils util;

	public UserServiceImpl() {
	}
	
	@Autowired
	public UserServiceImpl(Utils util) {
		this.util = util;
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

		return user;
	}
}
