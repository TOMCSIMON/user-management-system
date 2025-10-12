package com.tom.User_Management.repository;

import com.tom.User_Management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// USAGE - SPRING JPA HANDLES DATABASE OPERATIONS AUTOMATICALLY, SAVING TIME AND REDUCING ERRORS
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}

// JpaRepository - IS A SPRING DATA JPA INTERFACE THAT PROVIDES READY-MADE METHODS FOR CRUD operations.
// <User> → THE ENTITY TYPE
// <Long> → THE TYPE OF THE ENTITY’S PRIMARY KEY (userid)

/*  save(User user)     - INSERT OR UPDATE
    findById(Long id)   - FIND BY PRIMARY KEY
    findAll()           - GET ALL USERS
    delete(User user)   - DELETE A USER
    existsById(Long id) - CHECK IF USER EXISTS
*/
