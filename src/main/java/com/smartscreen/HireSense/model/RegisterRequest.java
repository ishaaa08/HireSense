package com.smartscreen.HireSense.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message="your name is required!")
    private String name;
    @Email
    @NotBlank(message="email is required!")
    private String email;
    @NotBlank(message="password is required!")
    private String password;


}
