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

// JpaRepository - IS A SPRING DATA JPA INTERFACE - PROVIDES READY-MADE METHODS FOR CRUD OPERATIONS.
// <User> → THE ENTITY TYPE
// <Long> → THE TYPE OF THE ENTITY’S PRIMARY KEY (userid)

/*  void save(User user)     - INSERT OR UPDATE
    user findById(Long id)   - FIND A USER BY PRIMARY KEY
    list findAll()           - GET ALL USERS
    void delete(User user)   - DELETE A USER
    user existsById(Long id) - CHECK IF USER EXISTS IN DB
*/
