package com.tom.User_Management.service;

import com.tom.User_Management.model.User;
import com.tom.User_Management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

// SPRING SECURITY USES THIS CLASS TO LOAD THE USER FROM DB BY USERNAME DURING LOGIN.

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        // RETRIEVE A USER FROM THE DATABASE BASED ON THEIR USERNAME.
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found!!"));

        // CONVERT ROLE FROM ENTITY TO SPRING SECURITY AUTHORITY
        GrantedAuthority authority =
                new SimpleGrantedAuthority(user.getRole().getRoleName().name());


        // RETURN SPRING SECURITY USER WITH ROLE AUTHORITY
        // CREATES A NEW INSTANCE OF SPRING SECURITY'S DEFAULT USER CLASS.
        // THIS CLASS HOLDS THE ESSENTIAL INFORMATION ABOUT AN AUTHENTICATED USER.
        return new org.springframework.security.core.userdetails.User(

                user.getUserName(), // RETRIEVES THE USERNAME OF THE USER.
                user.getPassword(),    // RETRIEVES THE USER'S PASSWORD MUST BE ENCODED
                Collections.singletonList(authority)
        );

    }
}
