package com.synergisticit.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.synergisticit.service.UserDetailsServiceImpl;

import static org.springframework.security.config.Customizer.withDefaults;


//
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
//	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
//	@Bean                                                             
//	public UserDetailsService userDetailsService() throws Exception {
//		// ensure the passwords are encoded properly
//		var userService = new InMemoryUserDetailsManager();
//		var user1 = User.withUsername("test")
//				.password(bCrypt.encode("test"))
//				.authorities("User")
//				.build();
//		
//		userService.createUser(user1);
//		return userService;
//		
//	}
	
	@Bean
	AuthenticationProvider authenticationProvider() throws Exception {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(bCrypt);
		return authenticationProvider;
		
	}
	
	@Bean 
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authenticationProvider());
		return authenticationManagerBuilder.build();
	}


	@Bean                                                            
	public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeHttpRequests(auth -> auth
//			.requestMatchers("/api/flight/**").permitAll()
			.anyRequest().authenticated()
		).httpBasic(withDefaults())	
		.formLogin(withDefaults());
			
			
			
		return http.build();
	}
	
}
