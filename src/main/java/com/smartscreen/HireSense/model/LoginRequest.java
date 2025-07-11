package com.smartscreen.HireSense.model;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LoginRequest {

    @Email
    private String email;
    private String password;

}
