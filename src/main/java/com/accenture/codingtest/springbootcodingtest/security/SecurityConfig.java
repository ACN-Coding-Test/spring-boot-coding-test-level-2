package com.accenture.codingtest.springbootcodingtest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/v1/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/projects/**").hasRole("PRODUCT_OWNER")
                .antMatchers(HttpMethod.POST, "/api/v1/tasks/**").hasRole("PRODUCT_OWNER")
                .antMatchers(HttpMethod.PUT, "/api/v1/tasks/**").hasRole("PRODUCT_OWNER")
                .antMatchers(HttpMethod.PATCH, "/api/v1/tasks/**").hasRole("PRODUCT_OWNER")
                .antMatchers(HttpMethod.DELETE, "/api/v1/tasks/**").hasRole("PRODUCT_OWNER")
                .and().httpBasic()
                .and().csrf().disable();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder()) // Use the password encoder here
                .withUser("admin")
                .password(passwordEncoder().encode("admin")) // Encode the password
                .roles("ADMIN")
                .and()
                .withUser("product_owner")
                .password(passwordEncoder().encode("password")) // Encode the password
                .roles("PRODUCT_OWNER");
    }

}
