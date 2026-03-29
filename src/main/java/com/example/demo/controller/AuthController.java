package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.advices.ApiResponse;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignUpDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserImpleService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	private final UserImpleService userImpleService;
	
	
	public AuthController(AuthService authService, UserImpleService userImpleService) {
		this.authService = authService;
		this.userImpleService = userImpleService;
	}
	
	@PostMapping("/signUp")
	public ResponseEntity<?> singUp(@RequestBody SignUpDTO signUpDTO) {
		
		UserDTO userDTO = authService.signUp(signUpDTO);
		return ResponseEntity.ok(userDTO);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
		
		System.out.println(loginDTO.getName());
		System.out.println(loginDTO.getPassword());
		
		String token = authService.login(loginDTO);
		
		Cookie cookie = new Cookie("jwtToken", token);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setMaxAge(24 * 60 * 60); // 24 Hours
		
		response.addCookie(cookie);
		
		//return ResponseEntity.ok("Your Token is: " + token);
		return ResponseEntity.ok(Map.of("message", "Your Token is: " + token));
	}
	
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllRecords() {
		List<UserDTO> response = userImpleService.findAllTheRecord();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
