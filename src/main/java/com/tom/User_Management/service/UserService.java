package com.tom.User_Management.service;

import com.tom.User_Management.model.User;

import java.util.List;


public interface UserService {

    // ABSTRACT METHODS
    void registerUser(User user);                  // FOR SIGNUP
    User loginUser(String name, String password); // FOR LOGIN
    List<User> getAllUsers();                      // FOR ADMIN OR DASHBOARD
    User getUserByEmail(String email);             // FOR CHECK DUPLICATES
    void deleteUser(Long id);
}
