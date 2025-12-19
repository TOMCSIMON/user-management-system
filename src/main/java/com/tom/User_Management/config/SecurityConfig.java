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

// THIS CLASS CONFIGURES SPRING SECURITY FOR THE APPLICATION — DEFINES LOGIN, LOGOUT AND ACCESS RULES.

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private CustomUserDetailsService customUserDetailsService;
  @Autowired
  private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(
            AbstractHttpConfigurer
                ::disable) // DISABLE CSRF FOR NOW CSRF - CROSS-SITE REQUEST FORGERY
        .authorizeHttpRequests(
            auth -> auth
                    .requestMatchers("/signup", "/register", "/login", "/css/**", "/js/**").permitAll() // ALLOW PUBLIC URLS
                    .requestMatchers("/adduser").hasRole("ADMIN")
                    .anyRequest().authenticated() // REQUIRE AUTHENTICATION FOR ALL OTHER REQUESTS
            )
        .formLogin(
            form ->
                form // FORM BASED LOGIN
                    .loginPage("/login") // CUSTOM LOGIN PAGE URL
                    .successHandler(
                        customAuthenticationSuccessHandler) // HANDLES REDIRECTION BASED ON ROLES
                    .failureUrl("/login?error=true") // REDIRECT TO LOGIN PAGE IF LOGIN FAILS
                    .permitAll())
        .logout(
            logout ->
                logout
                    .logoutUrl("/logout") // SPECIFYING LOGOUT URL
                    .logoutSuccessUrl("/login?logout=true") // REDIRECT TO LOGIN WITH LOGOUT PARAMETER AFTER SUCCESSFUL LOGOUT
                    .invalidateHttpSession(true) // INVALIDATE SESSION
                    .deleteCookies("JSESSIONID") // Delete session cookie
                    .permitAll());

    return http.build();
  }
}

/*
 SecurityConfig class defines the Spring Security configuration for the application.

 Responsibilities:
 1. Configures URL access rules:
    - Allows public access to specific endpoints (e.g., /login, /signup, /register).
    - Requires authentication for all other endpoints.

2. Sets up form-based authentication:
    - Uses a custom login page at /login.
    - Redirects to /dashboard upon successful login.
    - Redirects to /login?error=true upon failed login.

 3. Configures logout behavior:
    - Clears session and cookies on logout.
    - Redirects to /login?logout=true after successful logout.

 4. Disables CSRF protection temporarily (for testing or non-browser clients).

 5. Defines a BCryptPasswordEncoder bean for secure password hashing.

 6. Integrates a custom UserDetailsService (CustomUserDetailsService)
    to authenticate users from the database.

 Future Enhancement:
 - Add role-based access control (e.g., restrict /admin/** to ROLE_ADMIN).
 - Add a CustomAuthenticationSuccessHandler for dynamic redirects.
*/
