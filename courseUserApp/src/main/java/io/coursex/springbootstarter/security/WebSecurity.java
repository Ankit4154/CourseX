package io.coursex.springbootstarter.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import io.coursex.springbootstarter.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private Environment env;
	private UserServiceImpl userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public WebSecurity(Environment env, UserServiceImpl userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.env = env;
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").permitAll().antMatchers("/login").permitAll()
		.antMatchers("/users/login").permitAll()
		.and()
		.addFilter(getAuthenticationFilter());
		// For accepting requests only from specific IPs
		// http.authorizeRequests().antMatchers("/**").hasIpAddress(env.getProperty("gateway.ip"));
		// http.authorizeRequests().antMatchers("/users/**").permitAll();
		http.headers().frameOptions().disable();
	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authFilter = new AuthenticationFilter();
        //authFilter.setFilterProcessesUrl("/users/login");
        //authFilter.setFilterProcessesUrl("/login");
		authFilter.setAuthenticationManager(authenticationManager());
		authFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
			 
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				response.setStatus(HttpStatus.OK.value());
 
			}
		});
		
		return authFilter;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}
}
