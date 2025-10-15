package com.tom.User_Management.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter // INSTRUCT LOMBOK TO AUTOMATICALLY GENERATE A DEFAULT GETTER AND SETTER METHOD
@Setter // FOR EACH NON-STATIC FIELD IN THAT CLASS.
@Entity
@ToString
@Table(name = "users")
public class User {

  // ENTITY FIELDS
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @NotBlank(message = "Username is required")
  @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters")
  @Column(nullable = false, name = "user_name") // REQUIRED FIELDS
  private String userName;

  @Email(message = "Invalid Email")
  @NotBlank(message = "Email is required")
  @Column(unique = true, name = "email") // EMAIL SHOULD UNIQUE
  private String email;


  @NotBlank(message = "Password is required")
  @Size(min = 8, message = "Password must be at least 8 characters")
  @Column(unique = true, name = "password") // PASSWORD SHOULD UNIQUE
  private String password;

  @Transient
  private String confirmPassword;

  // NO-ARGS CONSTRUCTOR
  public User() {} // JPA REQUIRES A NO-ARGUMENT CONSTRUCTOR FOR CREATE ENTITY OBJECTS

  // ARGUMENT CONSTRUCTOR
  // FOR CREATING A USER OBJECT EASILY IN ONE LINE, INSTEAD OF CALLING SETTERS INDIVIDUALLY.
  public User(Long userId, String userName, String email, String password) {

    this.userId = userId;
    this.userName = userName;
    this.email = email;
    this.password = password;
  }

  // FOR GETTERS AND SETTERS USAGE
  // ENCAPSULATION: FIELDS ARE PRIVATE → CANNOT BE ACCESSED DIRECTLY FROM OUTSIDE

}
