package com.smartscreen.HireSense.Controller;

import com.smartscreen.HireSense.Entity.LoginRequest;
import com.smartscreen.HireSense.Entity.RegisterRequest;
import com.smartscreen.HireSense.Entity.UserEntity;
import com.smartscreen.HireSense.Service.ServiceLayer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/HireSense")
public class SignupController {

    private final ServiceLayer serviceLayer;

    public SignupController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest userDetails){
        try {
            serviceLayer.save(userDetails);
            return ResponseEntity.ok("User registered successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest userDetails){
        try{
            serviceLayer.loginChecking(userDetails);
            return ResponseEntity.ok("User logged in successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/upload-resume")
    public ResponseEntity<String> uploadResume(@RequestHeader("X-User-Email") String email,@RequestParam("resume") MultipartFile resume){
        if(resume == null){
            return ResponseEntity.badRequest().body("no file exists");
        }
        try{
            serviceLayer.uploadResume(email,resume);
            return ResponseEntity.ok("file saved");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("file upload failed: "+e.getMessage());
        }
    }



}
