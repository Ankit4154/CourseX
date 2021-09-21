package io.coursex.springbootstarter.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.coursex.springbootstarter.model.LoginRequest;
import io.coursex.springbootstarter.model.User;
import io.coursex.springbootstarter.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private Environment env;
	private UserService userService;
	
	public AuthenticationFilter(Environment env, UserService userService, 
			AuthenticationManager authenticationManager) {
		this.env = env;
		this.userService = userService;
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		
		try {
		LoginRequest cred = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
		
		System.out.println("cred param email : "+cred.getEmail());
		System.out.println("cred param password : "+cred.getPassword());
		
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getPassword(), new ArrayList<>()));
		
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String userName = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
		User userDetails = userService.getUserDetailsByEmail(userName);
		String token = Jwts.builder()
				.setSubject(userDetails.getUserId())
				.setExpiration(new Date(System.currentTimeMillis()+ Long.parseLong(env.getProperty("token.expiration_time"))))
				.signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
				.compact();
		response.addHeader("token", token);
		response.addHeader("userId", userDetails.getUserId());
		
	}
	
	
	
}
