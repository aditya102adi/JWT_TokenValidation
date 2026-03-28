package com.example.demo.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {
	
	
	@Value("${jwt.scretKey}")
	private String jwtSecretKey;
	
	private SecretKey generateSecretKey() {
		return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	
	public String generateToken(User user) {
		
		return Jwts.builder().subject(user.getName())
		.claim("email", user.getEmail())
		.claim("role", Set.of("CUSTOMER", "ADMIN"))
		.issuedAt(new Date())
		.expiration(new Date(System.currentTimeMillis() + 1000 * 60))
		.signWith(generateSecretKey())
		.compact();
		
	}
	
	
	public String getNameFromToken(String token) {
		
		Claims claim = Jwts.parser()
				.verifyWith(generateSecretKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		
		return claim.getSubject();
	}
	
	
}
