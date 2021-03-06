package io.coursex.springbootstarter.controller;

import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

//import io.coursex.springbootstarter.exception.UserCustomException;
import io.coursex.springbootstarter.model.User;
import io.coursex.springbootstarter.model.UserResponse;
import io.coursex.springbootstarter.service.UserService;

@RestController
@RequestMapping(path = "/users")//, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class UserController {

	Map<String, User> users;
	@Autowired
	UserService userService;
	@Autowired
	Environment env;

	/*
	 * @GetMapping public Map<String, User> getUsers(@RequestParam(value = "page",
	 * defaultValue = "50", required = false) int page,
	 * 
	 * @RequestParam(value = "limit", defaultValue = "50") int limit,
	 * 
	 * @RequestParam(value = "sort", defaultValue = "desc", required = false) String
	 * sort) {
	 * 
	 * return users; }
	 */
	/*
	@GetMapping
	public Map<String, User> getUsers() {

		return users;
	}*/
	/*
	@GetMapping(path="/status/check")
	public ResponseEntity<User> getStatusCheck() {
		System.out.println("Inside status check");
		return new ResponseEntity<>(HttpStatus.OK);
	}*/
	
	
	@GetMapping(path="/status/check")
	public String getUsers() {

		return "Working on port : "+env.getProperty("local.server.port")
				+ ", with token ="+env.getProperty("token.secret");
	}
	
	@GetMapping(path = "/test")
	public String getUserTest() {
		return "Working on port : "+env.getProperty("local.server.port");
	}

	@GetMapping(path = "/{userId}")
	public ResponseEntity<UserResponse> getUser(@PathVariable String userId) {
		// testing exception handling, nullpointerexception handling
		// String temp = null;
		// temp.length();

		// testing custom user exception
		// if (true) {
		//	throw new UserCustomException("User service exception is thrown");
		// }
		/*
		if (users.containsKey(userId)) {
			return new ResponseEntity<User>(users.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}*/
		if(userService.getUserByUserId(userId) != null) {
			return new ResponseEntity<UserResponse>(userService.getUserByUserId(userId), HttpStatus.OK);
		}else {
			return new ResponseEntity<UserResponse>(HttpStatus.NO_CONTENT);
		}
		
	}
	/*
	@GetMapping(path = "/{userId}/response")
	public ResponseEntity<User> getUserResponseStatus(@PathVariable String userId) {
		// sending User body as 1st parameter and response status 202/Accepted as 2nd parameter
		return new ResponseEntity<User>(new User("Ankit", "Singh", "testing@gmail.com", userId), HttpStatus.ACCEPTED);
	}*/

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserResponse> addUser(@Valid @RequestBody User user) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		// using modelMapper validations for getting exact Entity object parameters in request
		User tempUser = modelMapper.map(user, User.class);
		//Created UserResponse model to send out only required/specific details/data
		UserResponse userResponse = modelMapper.map(userService.addUser(tempUser), UserResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
		//return new ResponseEntity<User>(userService.addUser(tempUser), HttpStatus.CREATED);
	}

	@PutMapping(path = "/{userId}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
		user.setEmail(users.get(userId).getEmail());
		user.setEncryptedPassword(users.get(userId).getEncryptedPassword());
		users.put(userId, user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {

		users.remove(userId);
		return ResponseEntity.noContent().build();

	}
}
