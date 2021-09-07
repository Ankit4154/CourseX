package io.coursex.springbootstarter.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.coursex.springbootstarter.model.User;

@RestController
@RequestMapping(path = "/users", produces = { MediaType.APPLICATION_JSON_VALUE, 
		MediaType.APPLICATION_XML_VALUE })
public class UserController {

	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "50", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {

		return "Get all Users called, page:" + page + " limit:" + limit + " sort:" + sort;
	}

	@GetMapping(path = "/{userId}")
	public User getUser(@PathVariable String userId) {
		System.out.println("Get required user :" + userId);
		return new User("Ankit", "Singh", "testing@gmail.com", "as");
	}

	@PostMapping
	public String addUser() {
		return "add User called";
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
