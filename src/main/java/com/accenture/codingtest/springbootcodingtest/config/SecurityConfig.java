package com.accenture.codingtest.springbootcodingtest.config;

import com.accenture.codingtest.springbootcodingtest.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/users").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/api/v1/users/{user_id}").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/v1/users").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/api/v1/users/{user_id}").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.PATCH, "/api/v1/users/{user_id}").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/api/v1/users/{user_id}").hasRole(Role.ADMIN.name())

                .antMatchers(HttpMethod.GET, "/api/v1/projects").hasAnyRole(Role.ADMIN.name(),Role.PROJECT_OWNER.name())
                .antMatchers(HttpMethod.GET, "/api/v1/projects/{project_id}").hasAnyRole(Role.ADMIN.name(),Role.PROJECT_OWNER.name())
                .antMatchers(HttpMethod.POST, "/api/v1/projects").hasRole(Role.PROJECT_OWNER.name())
                .antMatchers(HttpMethod.PUT, "/api/v1/projects/{project_id}").hasAnyRole(Role.ADMIN.name(),Role.PROJECT_OWNER.name())
                .antMatchers(HttpMethod.DELETE, "/api/v1/projects/{project_id}").hasRole(Role.ADMIN.name())

                .antMatchers(HttpMethod.GET, "/api/v1/tasks").hasAnyRole(Role.ADMIN.name(),Role.PROJECT_OWNER.name())
                .antMatchers(HttpMethod.GET, "/api/v1/tasks/{task_id}").hasAnyRole(Role.ADMIN.name(),Role.PROJECT_OWNER.name())
                .antMatchers(HttpMethod.POST, "/api/v1/tasks").hasRole(Role.PROJECT_OWNER.name())
                .antMatchers(HttpMethod.PUT, "/api/v1/tasks/{task_id}").hasRole(Role.PROJECT_OWNER.name())
                .antMatchers(HttpMethod.PATCH, "/api/v1/tasks/{task_id}").hasRole(Role.PROJECT_OWNER.name())
                .antMatchers(HttpMethod.DELETE, "/api/v1/tasks/{task_id}").hasAnyRole(Role.ADMIN.name(),Role.PROJECT_OWNER.name())
                .antMatchers(HttpMethod.PATCH, "/api/v1/tasks/{task_id}/status").hasRole(Role.TEAM_MEMBER.name())

                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
