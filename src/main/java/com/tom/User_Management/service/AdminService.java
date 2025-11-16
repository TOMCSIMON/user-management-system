package com.tom.User_Management.service;

import com.tom.User_Management.dto.UpdateUserDTO;
import com.tom.User_Management.dto.UserDTO;
import com.tom.User_Management.model.User;
import jakarta.validation.Valid;
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

  // FOR PAGINATED USER LIST IN ADMIN-DASHBOARD
  Page<User> getUsersPaginated(int page, int size);

  UserDTO getUserById(Long id);

  void updateUser(Long id, @Valid UpdateUserDTO UpdateUserserDTO);

}
