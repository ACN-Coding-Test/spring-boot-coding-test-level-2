package com.accenture.codingtest.springbootcodingtest.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private DataSource ds;

	@Autowired
	public void configureAMBuilder(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.jdbcAuthentication().dataSource(ds)
		.usersByUsernameQuery("select USERNAME,PASSWORD,true FROM USERS where USERNAME=?")
		.authoritiesByUsernameQuery("select USERNAME, ROLE FROM USERS where USERNAME=?");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		
		http
		.authorizeRequests()
		.antMatchers("/api/v1/tasks/{id}").hasRole("USER")
		.and()
		.authorizeRequests()
		.antMatchers("/api/v1/users/**").hasRole("ADMIN")
		.and()
		.authorizeRequests()
		.antMatchers("/api/v1/projects").hasRole("PRODUCT_OWNER")
		.and()
		.authorizeRequests()
		.antMatchers("/api/v1/tasks").hasRole("PRODUCT_OWNER")
		.anyRequest()
		.fullyAuthenticated()  
		.and()
		.httpBasic();
		

		http.csrf().disable();
	}

	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

}