package com.smartscreen.HireSense.controller;

import com.smartscreen.HireSense.model.LoginRequest;
import com.smartscreen.HireSense.model.RegisterRequest;
import com.smartscreen.HireSense.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/HireSense")
@RequiredArgsConstructor
public class SignupController extends ApiRestController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest userDetails) {

        userService.signUp(userDetails);
        return ResponseEntity.ok(userService.signUp(userDetails));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest userDetails) {

        userService.loginChecking(userDetails);
        return ResponseEntity.ok("User logged in successfully");
    }

}
