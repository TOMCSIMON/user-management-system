package com.tom.User_Management.repository;

import com.tom.User_Management.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// USAGE - SPRING JPA HANDLES DATABASE OPERATIONS AUTOMATICALLY, SAVING TIME AND REDUCING ERRORS
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Page<User> findByUserNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
      String userName,
      String email,
      org.springframework.data.domain.Pageable pageable
  );

  // OPTIONAL INTERFACE USAGE - AVOIDS NULL POINTER EXCEPTIONS
  Optional<User> findByEmail(String email);

  Optional<User> findByUserName(String userName);

  boolean existsByEmail(String email);
}


