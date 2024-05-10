package com.Boyas.Tropicales.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JWTutils jwTutils;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity security) throws Exception {
		return security.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(http ->{
				http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();
				http.requestMatchers(HttpMethod.POST, "/obtenerRegistro").hasAnyRole("ADMIN");
				http.requestMatchers(HttpMethod.DELETE, "/obtenerRegistro").hasAnyAuthority("DELETE");
				http.anyRequest().authenticated();})
				.addFilterBefore(new JWTokenValidator(jwTutils), BasicAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public AuthenticationManager manager (AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider provider (UserDetailsImp userDetails) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(password());
		authenticationProvider.setUserDetailsService(userDetails);
		return authenticationProvider;
	}
	
	@Bean
	public PasswordEncoder password () {
		return new BCryptPasswordEncoder();
	}
	
}
