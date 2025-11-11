package com.tom.User_Management.service;

import com.tom.User_Management.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdminService {

  Page<User> searchUsersPaginated(String keyword, int page, int size);

  // FOR ADMIN-DASHBOARD
  List<User> getAllUsers();

  // FOR CHECK USER BY NAME
  User getUserByName(String userName);

  // FOR CHECK USER BY EMAIL
  User getUserByEmail(String email);

  // FOR DELETING USER
  void deleteUser(Long id);

  Page<User> getUsersPaginated(int page, int size);
}
