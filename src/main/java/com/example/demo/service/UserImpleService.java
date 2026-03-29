package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserImpleService {

	private final UserRepository userRepository;
	
	private final ModelMapper modelMapper;
	
	public UserImpleService(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}
	

	public List<UserDTO> findAllTheRecord() {
		
		List<User> allData = userRepository.findAll();
		
		if(allData.isEmpty()) throw new RuntimeException("No Data Found");
		
		List<UserDTO> response = new ArrayList<>();
		for(User user: allData) {
			response.add(modelMapper.map(user, UserDTO.class));
		}
		
		return response;
	}
	
}
