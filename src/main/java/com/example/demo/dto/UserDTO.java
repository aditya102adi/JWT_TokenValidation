package com.example.demo.dto;

import com.example.demo.entity.enums.Role;

public class UserDTO {
	
	private Long id;
	private String email;
	private String name;
	private Role role;


	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
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
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserDTO(Long id, String email, String name, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.role = role;
	}
	
}
