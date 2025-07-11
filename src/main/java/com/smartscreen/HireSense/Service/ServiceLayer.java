package com.smartscreen.HireSense.Service;


import com.smartscreen.HireSense.Entity.LoginRequest;
import com.smartscreen.HireSense.Entity.RegisterRequest;
import com.smartscreen.HireSense.Entity.ResumeEntity;
import com.smartscreen.HireSense.Entity.UserEntity;
import com.smartscreen.HireSense.Repository.SaveDb;
import jakarta.transaction.Transactional;
import org.apache.tika.Tika;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceLayer {

    private final SaveDb saveDb;

    public ServiceLayer(SaveDb saveDb) {
        this.saveDb = saveDb;
    }

    public UserEntity save(RegisterRequest userDetails) {
        if(saveDb.existsByEmail(userDetails.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        UserEntity user = new UserEntity();
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return saveDb.save(user);

    }



    public void uploadResume(String email,MultipartFile resume) throws Exception {

        String fileName=resume.getOriginalFilename();
        Path dir= Paths.get("uploads/");
        if(!Files.exists(dir)){
            Files.createDirectories(dir);
        }
        Path path=dir.resolve(resume.getOriginalFilename());
        Files.write(path, resume.getBytes());

        Tika tika=new Tika();
        String extractedText = tika.parseToString(resume.getInputStream());

        String fileType = tika.detect(resume.getInputStream());
        UserEntity user = saveDb.findByEmail(email);
        ResumeEntity resumeEntity = new ResumeEntity();
        resumeEntity.setFileName(fileName);
        resumeEntity.setFilePath("uploads/" + fileName);
        resumeEntity.setFileType(fileType);
        resumeEntity.setResumeText(extractedText);
        resumeEntity.setUploadedAt(LocalDateTime.now());
        user.addResume(resumeEntity);

         saveDb.save(user);
    }

    public void loginChecking(LoginRequest userDetails) {
        String email=userDetails.getEmail();
        String password=userDetails.getPassword();

        UserEntity user = saveDb.findByEmail(email);

        if(user==null || !user.getPassword().equals(password)){
            throw new RuntimeException("Invalid email or password");
        }

    }


}
