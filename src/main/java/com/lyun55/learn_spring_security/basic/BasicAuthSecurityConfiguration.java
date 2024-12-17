package com.lyun55.learn_spring_security.basic;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
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
		
		http.headers().frameOptions().sameOrigin();
		return http.build();
	}
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails user = User.withUsername("lyun55")
//			.password("{noop}1234")
//			.roles("USER")
//			.build();
//		
//		UserDetails admin = User.withUsername("admin")
//				.password("{noop}1234")
//				.roles("ADMIN") // <- ENUM 으로 만드는게 베스트
//				.build();
//		return new InMemoryUserDetailsManager(user, admin);
//	}
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript(org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
				.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		
		UserDetails user = User.withUsername("lyun55")
			//.password("{noop}1234")
			.password("1234")
			.passwordEncoder(str -> passwordEncoder().encode(str))
			.roles("USER")
			.build();
		
		UserDetails admin = User.withUsername("admin")
				//.password("{noop}1234")
				.password("1234")
				.passwordEncoder(str -> passwordEncoder().encode(str))
				.roles("ADMIN","USER") // <- ENUM 으로 만드는게 베스트
				.build();
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.createUser(user);
		jdbcUserDetailsManager.createUser(admin);
		
		return jdbcUserDetailsManager;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
