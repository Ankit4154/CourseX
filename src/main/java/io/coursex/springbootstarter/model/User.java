package io.coursex.springbootstarter.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@NotNull(message="first name cannot be null")
	@Size(min=2, message="first name cannot be less than 2 characters")
	private String firstName;
	@NotNull(message="last name cannot be null")
	private String lastName;
	@NotNull(message="email cannot be null")
	private String email;
	@NotNull(message="password cannot be null")
	@Size(min=1,max=2, message="Password must be equal or maximum of 2 characters")
	private String password;

	public User() {
	}

	public User(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
