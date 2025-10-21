package com.tom.User_Management.service;

import com.tom.User_Management.dto.UserDTO;
import com.tom.User_Management.enums.RoleEnum;
import com.tom.User_Management.model.Role;
import com.tom.User_Management.model.User;
import com.tom.User_Management.repository.RoleRepository;
import com.tom.User_Management.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service // IT WILL AUTOMATICALLY CREATE A BEAN OF THIS CLASS
         // SO CONTROLLERS (OR OTHER SERVICES) CAN INJECT IT.
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;

  // CONSTRUCTOR INJECTION
  public UserServiceImpl(UserRepository userRepository,
                         PasswordEncoder passwordEncoder,
                         RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
  }

  @Transactional // THIS ENSURES THE TRANSACTION IS COMMITTED
  @Override
  public void registerUser(UserDTO userDTO) throws RuntimeException {

    log.info(">>> registerUser() called with: {}", userDTO.getUserName());

    if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
        throw new IllegalArgumentException("Passwords do not match!");
    }


    // CHECK FOR EXISTING EMAIL BY USING CUSTOM METHOD FROM UserRepository
    if(userRepository.existsByEmail(userDTO.getEmail())){
        throw new IllegalArgumentException("Email already exists!");
    }


    // MAP UserDTO TO User ENTITY & ENCODING PASSWORD
    User user = new User();
    user.setUserName(userDTO.getUserName());
    user.setEmail(userDTO.getEmail());
    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));


    // ROLE ASSIGNING TO EACH USER WITH ROLE_USER
    Role defaultRole = roleRepository.findByRoleName(RoleEnum.ROLE_USER)
                       .orElseThrow(() -> new RuntimeException("Default Role not found!"));
    user.setRole(defaultRole);
    log.info(">>> ROLE of the User: {}" , RoleEnum.ROLE_USER.name());

    // SAVE USER
    userRepository.save(user);

    log.info(">>> User saved to DB: {} (email: {})", user.getUserName(), user.getEmail());
  }
}
