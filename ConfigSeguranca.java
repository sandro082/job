package com.projeto.trabalho.seguranca.user;


import java.beans.Customizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class ConfigSeguranca   {

	/*
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("sandro").password("123").roles("ADMIN");	
		
	}*/
	
	/*
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User
				.withUsername("user")
				.password("123")
				.roles("USER")
				.build();
		UserDetails admin = User
				.withUsername("sandro")
				.password("123")
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user, admin);
	}*/
	
	
	
	
	
	/*
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		
		http.authorizeHttpRequests()
			.requestMatchers("/rh/pessoas", "/logout")
			.permitAll()
			.requestMatchers("rh/pessoas/nova")
			.hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.and()
			.httpBasic();
		return http.build();
		//.hasAnyRole("ADMIN", "USER")
	}*/


	    		

		
		@Bean
		public PasswordEncoder encoder() {
			return new BCryptPasswordEncoder();
		}

		@Bean
		public InMemoryUserDetailsManager userDetailsService() {
			UserDetails user = User
					.withUsername("user")
					.password(encoder().encode("123456"))
					.roles("USER")
					.build();
			UserDetails admin = User
					.withUsername("sandro")
					.password(encoder().encode("123456"))
					.roles("ADMIN")
					.build();
			return new InMemoryUserDetailsManager(user, admin);
		}
		
		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			/*http.authorizeHttpRequests( auth -> auth.anyRequest().authenticated() )
				.formLogin()
				.and()
				.httpBasic();*/
			http.authorizeHttpRequests()
				.requestMatchers("/rh/pessoas", "/logout")
				.permitAll()
				.requestMatchers("rh/pessoas/nova")
				.hasRole("ADMIN")
				//.hasAnyRole("ADMIN", "USER")
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.and()
				.httpBasic();
			return http.build();
		}
	}
	
	
	





































