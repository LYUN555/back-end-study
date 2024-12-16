package com.lyun55.learn_spring_security.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthSecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
									(auth) ->{
										auth.anyRequest().authenticated();
									});
		//http.formLogin(withDefaults());
		http.sessionManagement(
				session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				);
		http.httpBasic();
		
		http.csrf().disable();
		return http.build();
	}
}