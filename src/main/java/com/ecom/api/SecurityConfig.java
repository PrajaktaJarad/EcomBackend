package com.ecom.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception {
		
		  auth.inMemoryAuthentication() .withUser("harry")
		  .password(getPassEncoder().encode("potter")) .authorities("MANAGER") 
		  .and()
		  .withUser("ron") .password(getPassEncoder().encode("weasely"))
		  .authorities("ACCOUNTANT");
	 
	}
	
	/*
	 * private AuthenticationProvider getAuthProvider() { DaoAuthenticationProvider
	 * auth = new DaoAuthenticationProvider();
	 * auth.setUserDetailsService(myUserDetailsService);
	 * auth.setPasswordEncoder(getPassEncoder()); return auth; }
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		                   
		                   .antMatchers("/category").permitAll()
		                   .antMatchers("/review/product/{pid}").authenticated()
		                   .antMatchers(HttpMethod.POST,"/review/{pid}").authenticated()
		                   .anyRequest().permitAll()
		                   .and()
		                   .httpBasic()
		                   .and()
		                   .cors().disable()
		                   .csrf().disable();

	}
	
	@Bean
	public PasswordEncoder getPassEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
