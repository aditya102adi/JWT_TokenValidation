package com.example.demo.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignUpDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exceptions.ResourceAlreadyExists;
import com.example.demo.repository.UserRepository;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	
	private final TokenService tokenService;
	
	private final AuthenticationManager authenticationManager;
	
	
	public AuthService(
			
			UserRepository userRepository, 
			ModelMapper modelMapper,
			PasswordEncoder passwordEncoder,
			TokenService tokenService,
			AuthenticationManager authenticationManager) {
		
		
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.tokenService = tokenService;
		this.authenticationManager = authenticationManager;
	}
	
	
	public UserDTO signUp(SignUpDTO signUpDTO) {
		
		//Check if user is already present or not
		Optional<User> user = userRepository.findByEmail(signUpDTO.getEmail());
		if(user.isPresent()) {
			throw new ResourceAlreadyExists("User already present with email id: " + signUpDTO.getEmail());
		}
				
		//Create the new user 
		User newUser = modelMapper.map(signUpDTO, User.class);
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		
		User savedUser = userRepository.save(newUser);
		
		return modelMapper.map(savedUser, UserDTO.class);
	}


	/*public String login(LoginDTO loginDTO) {
		
		User user = userRepository.findByName(loginDTO.getName())
			.orElseThrow(() -> new ResourceNotFoundException("Name is Invalid"));
		
		//Validate the password
		if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
			throw new ResourceNotFoundException("Password is Invalid");
		}
		
		
		return tokenService.generateToken(user);
		 
	}*/
	
	public String login(LoginDTO loginDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getName(), loginDTO.getPassword())
		);
		
		User user = (User) authentication.getPrincipal();
		
		return tokenService.generateToken(user);
	}

}
