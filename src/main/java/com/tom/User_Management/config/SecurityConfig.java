package com.tom.User_Management.config;

import com.tom.User_Management.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(AbstractHttpConfigurer::disable) // DISABLE CSRF FOR NOW CSRF - CROSS-SITE REQUEST FORGERY
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signup", "/register", "/login","/admin-login", "/css/**", "/js/**").permitAll() // ALLOW PUBLIC URLS
                        .anyRequest().authenticated() // REQUIRE AUTHENTICATION FOR ALL OTHER REQUESTS
                )
                .formLogin(form -> form // FORM BASED LOGIN
                        .loginPage("/login") // CUSTOM LOGIN PAGE URL
                        .defaultSuccessUrl("/dashboard", true) // REDIRECT TO HOME IF LOGIN SUCCESSFUL
                        .failureUrl("/login?error=true") // REDIRECT TO LOGIN PAGE IF LOGIN FAILS
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // SPECIFYING LOGOUT URL
                        .logoutSuccessUrl("/login?logout=true") // REDIRECT TO /LOGIN WITH LOGOUT PARAMETER AFTER SUCCESSFUL LOGOUT
                        .invalidateHttpSession(true) // INVALIDATE SESSION
                        .deleteCookies("JSESSIONID") // Delete session cookie
                        .permitAll()
                );

        return http.build();

    }


}

/* This config demonstrates basic CONFIGURATION FOR ALLOWING PUBLIC ACCESS to /signup, /register  ,/css/**, /js/** paths,
requiring authentication for all other requests, and enabling FORM-BASED login and logout.*/


