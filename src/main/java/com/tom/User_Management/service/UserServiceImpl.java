package com.tom.User_Management.service;

import com.tom.User_Management.model.User;
import com.tom.User_Management.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // IT WILL AUTOMATICALLY CREATE A BEAN OF THIS CLASS SO CONTROLLERS (OR OTHER SERVICES) CAN
         // INJECT IT.
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  // CONSTRUCTOR INJECTION
  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User registerUser(User user) {

    // CHECK FOR EXISTING EMAIL
    userRepository.findByEmail(user.getEmail())
            .ifPresent(existing -> {
              throw new IllegalArgumentException("Email already exists!!");
            });

    // ENCODE PASSWORD
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    // SAVE AND RETURN USER
    return userRepository.save(user);
  }

  @Override
  public User loginUser(String email, String password) {
    return null;
  }

  @Override
  public List<User> getAllUsers() {
    return List.of();
  }

  @Override
  public User getUserByEmail(String email) {
    return null;
  }

  @Override
  public void deleteUser(Long id) {}
}
