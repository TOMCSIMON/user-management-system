package com.tom.User_Management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateUserDTO {

    private Long id;

    @NotBlank(message = "User name can not be empty")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String userName;

    @NotBlank(message = "Email can not be empty")
    @Email(message = "Email should be valid")
    private String email;

    private String password;
}
