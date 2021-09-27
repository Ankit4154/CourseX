package io.coursex.springbootstarter.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class User implements Serializable {

	private static final long serialVersionUID = -2735863428281942275L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull(message="first name cannot be null")
	@Column(nullable=false, length=50)
	//@Size(min=2, message="first name cannot be less than 2 characters")
	private String firstName;
	@NotNull(message="last name cannot be null")
	@Column(nullable=false, length=50)
	private String lastName;
	@NotNull(message="email cannot be null")
	@Column(nullable=false, length=120, unique=true)
	private String email;
	//@Size(min=1,max=2, message="Password must be equal or maximum of 2 characters")
	@Column(nullable=false, unique=true)
	private String encryptedPassword;
	@Column(nullable=false, unique=true)
	private String userId;
	@Transient
	private String password;
	
	public User() {
	}

	public User(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
