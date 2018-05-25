package com.campusnum.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	@Size(min=2, max=15)
	private String firstName;
	
	@NotEmpty
	@Size(min=2, max=15)
	private String lastName;
	
	@NotEmpty
	@Email
	private String email;
	
	public User() {
		
	}

	public Long getId() {
		return id;
	}
	
	public User(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this. lastName = lastName;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setId(Long id) {
		this.id = id;		
	
	}
	
	

}
