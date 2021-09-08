package io.coursex.springbootstarter.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.coursex.springbootstarter.model.User;

@RestController
@RequestMapping(path = "users", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class UserController {

	Map<String, User> users;

	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "50", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {

		return "Get all Users called, page:" + page + " limit:" + limit + " sort:" + sort;
	}

	@GetMapping(path = "/{userId}")
	public ResponseEntity<User> getUser(@PathVariable String userId) {
		
		if(users.containsKey(userId)) {
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(path = "/{userId}/response")
	public ResponseEntity<User> getUserResponseStatus(@PathVariable String userId) {
		// sending User body as 1st parameter and response status 202/Accpeted as 2nd
		// parameter
		return new ResponseEntity<User>(new User("Ankit", "Singh", "testing@gmail.com", userId), HttpStatus.ACCEPTED);
	}

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		// Automatic constraints validation on User properties via Valid annotation		
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		if(users == null) {
			users = new HashMap<>();
		}
		users.put(userId, user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{userId}")
	public String updateUser(@PathVariable String userId) {
		return "update User called, userid : " + userId;
	}

	@DeleteMapping(path = "/{userId}")
	public String deleteUser(@PathVariable String userId) {
		return "delete User called, userid : " + userId;
	}
}
