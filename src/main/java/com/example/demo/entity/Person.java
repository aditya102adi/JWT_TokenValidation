package com.example.demo.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person implements UserDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String password;
	
	private Set<Role> roles = new HashSet<>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		// I have to provide the 
		Set<GrantedAuthority> authrority = new HashSet<>();
		
		for(Role role: roles) {
			authrority.add(new SimpleGrantedAuthority("ROLE_" + role));
		}
		
		return authrority;		
	}
	
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
