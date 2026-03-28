package com.example.demo.dto;

import com.example.demo.entity.enums.Role;

public class SignUpDTO {
	
	private String email;
	private String name;
	private String password;
	private Role role; 
	
	
	public SignUpDTO() {
		
	} 

	public SignUpDTO(String email, String name, String password, Role role) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.role = role;
	}



	public String getEmail() {
		return email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
