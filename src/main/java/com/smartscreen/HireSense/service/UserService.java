package com.smartscreen.HireSense.service;


import com.smartscreen.HireSense.exception.InvalidPassword;
import com.smartscreen.HireSense.exception.UserAlreadyExists;
import com.smartscreen.HireSense.exception.UserNotFound;
import com.smartscreen.HireSense.model.LoginRequest;
import com.smartscreen.HireSense.model.RegisterRequest;
import com.smartscreen.HireSense.entity.UserEntity;
import com.smartscreen.HireSense.repository.ResumeDao;
import com.smartscreen.HireSense.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final ResumeDao resumeDao;

    public String signUp(RegisterRequest userDetails) {
        if (userDao.existsByEmail(userDetails.getEmail())) {
            throw new UserAlreadyExists("Email already exists");
        }

        UserEntity user = new UserEntity();
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        userDao.save(user);

        return String.format("User with email %s registered successfully", userDetails.getEmail());
    }

    public void loginChecking(LoginRequest userDetails) {

        UserEntity user = userDao.findFirstByEmail(userDetails.getEmail());
        if (user == null)
            throw new UserNotFound("No user found with email " + userDetails.getEmail());
        if (!user.getPassword().equals(userDetails.getPassword())) {
            throw new InvalidPassword("Please enter a valid password");
        }
    }

}
