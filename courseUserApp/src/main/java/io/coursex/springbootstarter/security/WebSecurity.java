package io.coursex.springbootstarter.security;

import org.springframework.context.annotation.Configuration;
<<<<<<< HEAD
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
=======
>>>>>>> parent of 5f7423d... Added security filter to get requests from specific IP
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.coursex.springbootstarter.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

<<<<<<< HEAD
	private Environment env;
	private UserServiceImpl userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public WebSecurity(Environment env, UserServiceImpl userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.env = env;
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

=======
>>>>>>> parent of 5f7423d... Added security filter to get requests from specific IP
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
<<<<<<< HEAD
		http.authorizeRequests().antMatchers("/users/**").permitAll().and().addFilter(getAuthenticationFilter());
		// For accepting requests only from specific IPs
		// http.authorizeRequests().antMatchers("/**").hasIpAddress(env.getProperty("gateway.ip"));
=======
		http.authorizeRequests().antMatchers("/users/**").permitAll();
>>>>>>> parent of 5f7423d... Added security filter to get requests from specific IP
		http.headers().frameOptions().disable();
	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authFilter = new AuthenticationFilter();
		authFilter.setAuthenticationManager(authenticationManager());
		return authFilter;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}
}
