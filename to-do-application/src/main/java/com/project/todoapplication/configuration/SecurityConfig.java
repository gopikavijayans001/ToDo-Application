package com.project.todoapplication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@Configuration

public class SecurityConfig {
        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST,"/user","/project","/project/todo","project/export-gist").permitAll().requestMatchers(HttpMethod.GET,"/project","/project/todo").permitAll().requestMatchers(HttpMethod.PUT,"/project","/project/todo").permitAll().requestMatchers(HttpMethod.DELETE,"/project","/project/todo").permitAll().requestMatchers(HttpMethod.POST,"/user/login").authenticated());
            http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER));
            http.httpBasic();
            http.csrf(csrf -> csrf.disable());
            return http.build();

        }

        @Bean
        public UserDetailsService userDetailService() {
            UserDetails user = User.withUsername("user").password("{noop}user").build();
            return new InMemoryUserDetailsManager(user);
        }

    }

