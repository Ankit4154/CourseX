package io.coursex.springbootstarter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.coursex.springbootstarter.model.Course;
import io.coursex.springbootstarter.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public List<User> findByUserId(Long id);
}
