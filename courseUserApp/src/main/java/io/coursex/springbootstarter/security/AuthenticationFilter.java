package io.coursex.springbootstarter.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.coursex.springbootstarter.model.LoginRequest;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

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
		super.successfulAuthentication(request, response, chain, authResult);
	}
	
	
	
}
