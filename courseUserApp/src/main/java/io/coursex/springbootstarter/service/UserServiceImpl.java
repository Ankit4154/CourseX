package io.coursex.springbootstarter.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import feign.FeignException;
import io.coursex.springbootstarter.Utils;
import io.coursex.springbootstarter.model.AlbumResponse;
import io.coursex.springbootstarter.model.AlbumsServiceClient;
import io.coursex.springbootstarter.model.User;
import io.coursex.springbootstarter.model.UserResponse;
import io.coursex.springbootstarter.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	Map<String, User> users;
	Utils util;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	RestTemplate restTemplate;
	AlbumsServiceClient albumsServiceClient;
	Environment env;

	UserRepository userRepository;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public UserServiceImpl() {
	}

	@Autowired
	public UserServiceImpl(Utils util, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			RestTemplate restTemplate, Environment env, AlbumsServiceClient albumsServiceClient) {
		this.util = util;
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.restTemplate = restTemplate;
		this.env = env;
		this.albumsServiceClient = albumsServiceClient;
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
		if (user == null) {
			throw new UsernameNotFoundException(username);
		} else {
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPassword(),
					true, true, true, true, new ArrayList<>());
		}
	}

	@Override
	public User getUserDetailsByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return new ModelMapper().map(user, User.class);
	}

	@Override
	public UserResponse getUserByUserId(String userId) {
		User user = userRepository.findByUserId(userId);
		if (user == null)
			throw new UsernameNotFoundException("user not found");

		ModelMapper modelMapper = new ModelMapper();
		UserResponse userResponse = modelMapper.map(user, UserResponse.class);

		// Using RestTemplate as HTTP Client
		/*
		String albumUrl = String.format(env.getProperty("albums.url"), userId);

		ResponseEntity<List<AlbumResponse>> albumListResponse = restTemplate.exchange(albumUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<AlbumResponse>>() {
				});
		List<AlbumResponse> albumList = albumListResponse.getBody();
		*/
		
		// Testing exception handling of Feign Client
		// try {
			logger.info("Before calling albums microservices");
			List<AlbumResponse> albumList = albumsServiceClient.getAlbums(userId);
			logger.info("After calling albums microservices");
			//List<AlbumResponse> albumList = null;
			userResponse.setAlbums(albumList);
		/*}catch(FeignException fe){
			logger.error(fe.getLocalizedMessage());
		}*/
		return userResponse;
	}
}
