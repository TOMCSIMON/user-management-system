package com.tom.User_Management.service;

import com.tom.User_Management.model.User;
import com.tom.User_Management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    public UserRepository userRepository;


    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUserByName(String userName) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {

        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
