package com.tom.User_Management.service;

import com.tom.User_Management.model.User;

import java.util.List;

public interface AdminService {

  // FOR ADMIN-DASHBOARD
  List<User> getAllUsers();

  // FOR CHECK USER BY NAME
  User getUserByName(String userName);

  // FOR CHECK USER BY EMAIL
  User getUserByEmail(String email);

  // FOR DELETING USER
  void deleteUser(Long id);
}
