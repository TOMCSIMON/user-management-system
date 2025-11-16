package com.tom.User_Management.service;

import com.tom.User_Management.dto.UpdateUserDTO;
import com.tom.User_Management.dto.UserDTO;
import com.tom.User_Management.model.User;
import com.tom.User_Management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

   public AdminServiceImpl(
           UserRepository userRepository,
           PasswordEncoder passwordEncoder
   ){
       this.userRepository = userRepository;
       this.passwordEncoder = passwordEncoder;
   }


  @Override
  public Page<User> searchUsersPaginated(String keyword, int page, int size) {
    return userRepository.findByUserNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
        keyword, keyword, PageRequest.of(page, size));
  }

  @Override
  public List<User> getAllUsers() {

    return userRepository.findAll();
  }

  @Override
  public User getUserByName(String userName) {

    return userRepository.findByUserName(userName).orElse(null);
  }

  @Override
  public User getUserByEmail(String email) {

    return userRepository.findByEmail(email).orElse(null);
  }

  @Override
  public void deleteUser(Long id) {

    User user =
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

    if(user.getRole().getRoleId() == 2) {

        throw  new RuntimeException("Can not delete an Admin");
    }

    userRepository.deleteById(id);
  }

  @Override
  public Page<User> getUsersPaginated(int page, int size) {

      return userRepository.findAll(PageRequest.of(page, size));
  }


  @Override
  public UserDTO getUserById(Long id) {

      User user = userRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("user not found" + id));

      // CONVERTING ENTITY TO DTO
      UserDTO userDTO = new UserDTO();
      userDTO.setId(user.getUserId());
      userDTO.setUserName(user.getUserName());
      userDTO.setEmail(user.getEmail());

      return userDTO;
  }



    @Override
    public void updateUser(Long id, UpdateUserDTO updateUserDTO) {

      // Fetching the existing user from DB using the id
      User user = userRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("User not found with id:" + id));

      // UpdateUserDTO TO USER MODEL CONVERSION
      user.setUserName(updateUserDTO.getUserName());
      user.setEmail(updateUserDTO.getEmail());

      if(updateUserDTO.getPassword() != null && !updateUserDTO.getPassword().isBlank()){
          String encodedPassword = passwordEncoder.encode(updateUserDTO.getPassword());
          user.setPassword(encodedPassword);
      }

      userRepository.save(user);

    }

}
