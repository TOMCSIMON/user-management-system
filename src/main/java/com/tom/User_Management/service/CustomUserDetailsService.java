package com.tom.User_Management.service;

import com.tom.User_Management.model.User;
import com.tom.User_Management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        // RETRIEVE A USER FROM THE DATABASE BASED ON THEIR USERNAME.
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found!!"));


        // CREATES A NEW INSTANCE OF SPRING SECURITY'S DEFAULT USER CLASS.
        // THIS CLASS HOLDS THE ESSENTIAL INFORMATION ABOUT AN AUTHENTICATED USER.
        return new org.springframework.security.core.userdetails.User(

                user.getUserName(), // RETRIEVES THE USERNAME OF THE USER.
                user.getPassword(),    // RETRIEVES THE USER'S PASSWORD MUST BE ENCODED
                new ArrayList<>()
        );

    }
}
