package com.accenture.codingtest.springbootcodingtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, UserDetailsService customUserDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder())
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/projects").hasRole("PRODUCT_OWNER")
                .antMatchers(HttpMethod.POST, "/api/v1/tasks").hasRole("PRODUCT_OWNER")
                .antMatchers(HttpMethod.POST, "/api/v1/projects/assignTasks/{project_id}/{user_id}").hasRole("PRODUCT_OWNER")
                .antMatchers(HttpMethod.PATCH, "/api/v1/tasks/{task_id}").hasRole("MEMBER")
                .antMatchers(HttpMethod.PUT, "/api/v1/tasks/{task_id}").hasRole("MEMBER")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
