package com.tom.User_Management.service;

import com.tom.User_Management.model.User;
import com.tom.User_Management.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // IT WILL AUTOMATICALLY CREATE A BEAN OF THIS CLASS SO CONTROLLERS (OR OTHER SERVICES) CAN INJECT IT.
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  // CONSTRUCTOR INJECTION
  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional // THIS ENSURES THE TRANSACTION IS COMMITTED
  @Override
  public void registerUser(User user) {

    System.out.println(">>> registerUser() called with: " + user);

    // CHECK FOR EXISTING EMAIL
    userRepository
        .findByEmail(user.getEmail())
        .ifPresent(
            existing -> {
              throw new IllegalArgumentException("Email already exists!!");
            });

    // ENCODE PASSWORD
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);

    // SAVE USER
    User saved = userRepository.save(user);
    System.out.println(">>> User saved to DB: " + saved);


  }

  @Override
  public User loginUser(String name, String password) {
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
