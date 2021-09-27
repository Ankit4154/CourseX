package io.coursex.springbootstarter.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import io.coursex.springbootstarter.model.User;
import io.coursex.springbootstarter.model.UserResponse;

public interface UserService extends UserDetailsService {
	
	User addUser(User user);
	User getUserDetailsByEmail(String email);
	UserResponse getUserByUserId(String userId);
}
