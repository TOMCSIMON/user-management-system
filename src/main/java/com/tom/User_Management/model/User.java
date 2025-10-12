package com.tom.User_Management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter // INSTRUCT LOMBOK TO AUTOMATICALLY GENERATE A DEFAULT GETTER AND SETTER METHOD
@Setter // FOR EACH NON-STATIC FIELD IN THAT CLASS.
@Entity
@Table(name = "users")
public class User {



  // ENTITY FIELDS
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(nullable = false) // REQUIRED FIELDS
  private String userName;

  @Column(unique = true) // EMAIL SHOULD UNIQUE
  private String email;

  @Column(unique = true) // PASSWORD SHOULD UNIQUE
  private String password;




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


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
