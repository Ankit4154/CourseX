package io.coursex.springbootstarter.repository;

import org.springframework.data.repository.CrudRepository;

import io.coursex.springbootstarter.model.User;
import io.coursex.springbootstarter.model.UserResponse;

public interface UserRepository extends CrudRepository<User, Long> {

	//public List<User> findById(Long id);
	User findByEmail(String email);
	User findByUserId(String userId);
}
