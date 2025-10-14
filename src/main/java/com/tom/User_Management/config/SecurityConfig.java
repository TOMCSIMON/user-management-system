package com.tom.User_Management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // disable CSRF for now
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signup", "/css/**", "/js/**").permitAll() // public URLs
                        .anyRequest().authenticated() // other pages require login
                )
                .formLogin(form -> form
                        .loginPage("/login") // custom login page URL
                        .permitAll()
                );

        return http.build();
    }


}
