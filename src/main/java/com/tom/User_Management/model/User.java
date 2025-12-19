package com.tom.User_Management.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "users")
public class User {

  // ENTITY FIELDS
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;


  @Column(nullable = false, name = "user_name") // REQUIRED FIELDS
  private String userName;


  @Column(unique = true, name = "email") // EMAIL SHOULD UNIQUE
  private String email;

  @Column(nullable = false, name = "password")  // REQUIRED FIELDS
  private String password;


  // THE @ManyToOne ANNOTATION ESTABLISHES THE RELATIONSHIP
  @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
  @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false) // DEFINES THE FOREIGN KEY COLUMN
  private Role role;



  // NO-ARGS CONSTRUCTOR - USAGE:
  // JPA REQUIRES A NO-ARGUMENT CONSTRUCTOR FOR CREATE ENTITY OBJECTS

  // ARGUMENT CONSTRUCTOR - USAGE:
  // FOR CREATING A USER OBJECT EASILY IN ONE LINE, INSTEAD OF CALLING SETTERS INDIVIDUALLY.

  // FOR GETTERS AND SETTERS - USAGE:
  // ENCAPSULATION: FIELDS ARE PRIVATE → CANNOT BE ACCESSED DIRECTLY FROM OUTSIDE(VIEW)

}
