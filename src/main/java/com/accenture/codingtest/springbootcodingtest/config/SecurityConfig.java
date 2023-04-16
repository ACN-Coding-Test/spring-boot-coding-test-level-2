package com.accenture.codingtest.springbootcodingtest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		super.configure(auth);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/v1/**", "/swagger-resources/configuration/ui", "/swagger-resources",
				"/swagger-ui.html", "/webjars/**")
		.permitAll()
		.antMatchers("/v1/User/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/v1/Task/**", "/v1/Project/**").access("hasRole('ROLE_PROJECT_OWNER')")

		.anyRequest().authenticated().and().httpBasic();

	}
//	  @Override
//	    public void configure(WebSecurity web)  {
//	        web.ignoring().antMatchers("/v3/api-docs",
//	                "/swagger-ui.html",
//	                "/swagger-ui/**");
//	  }
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	

}
