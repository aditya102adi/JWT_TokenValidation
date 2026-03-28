package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.User;
import com.example.demo.entity.enums.Role;
import com.example.demo.service.TokenService;

@SpringBootTest
class TokenAuthApplicationTests {
	
	@Autowired
	private TokenService tokenService;
	

	@Test
	void contextLoads() {
		
		User user = new User("Aditya", "aditya102", "adi", Role.ADMIN);
		
		String token = tokenService.generateToken(user);
		
		String name = tokenService.getNameFromToken(token);
		
		System.out.println(token);
		System.out.println(name);
		
	}

}
