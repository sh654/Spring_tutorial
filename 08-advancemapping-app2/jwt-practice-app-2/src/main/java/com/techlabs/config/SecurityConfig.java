package com.techlabs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.techlabs.security.JwtAuthenticationEntryPoint;
import com.techlabs.security.JwtAuthenticationFilter;



import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import lombok.RequiredArgsConstructor;

@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final UserDetailsService userDetailsService;
	private final JwtAuthenticationFilter authenticationFilter;
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.csrf(csrf-> csrf.disable())
		.cors(withDefaults())
		.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
		.authorizeHttpRequests(request -> {
			request.requestMatchers("/api/register", "/api/login").permitAll();
            request.requestMatchers(HttpMethod.GET, "/api/**").authenticated();
            request.requestMatchers(HttpMethod.POST, "/api/**").authenticated();
            request.requestMatchers(HttpMethod.PUT, "/insuranceapp/**").authenticated();
            request.requestMatchers(HttpMethod.DELETE, "/insuranceapp/**").authenticated();
            request.anyRequest().authenticated();
		}).exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
		.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
}
