package com.example.demo.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecutityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())   // disable CSRF (for APIs)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()   // allow all requests
            )
            .formLogin(form -> form.disable())   // disable default login form
            .httpBasic(basic -> basic.disable()); // disable basic auth

        return http.build();
    }
	
}
