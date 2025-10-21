package com.tom.User_Management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupDTO {


    @NotBlank(message = "User name can not be empty")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String userName;



    @NotBlank(message = "Email can not be empty")
    @Email(message = "Email should be valid")
    private String email;



    @NotBlank(message = "Password cannot be empty")
    @Size( min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
