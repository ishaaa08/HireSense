package com.smartscreen.HireSense.service;

import com.smartscreen.HireSense.entity.ResumeEntity;
import com.smartscreen.HireSense.entity.UserEntity;
import com.smartscreen.HireSense.exception.DataValidationException;
import com.smartscreen.HireSense.exception.UserNotFound;
import com.smartscreen.HireSense.repository.ResumeDao;
import com.smartscreen.HireSense.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final UserDao userDao;
    private final ResumeDao resumeDao;

    public void uploadResume(String email, MultipartFile resume) throws IOException, TikaException {

        UserEntity user = userDao.findFirstByEmail(email);
        if (user == null)
            throw new UserNotFound("No user found with email " + email);

        if (resume == null || resume.isEmpty())
            throw new DataValidationException("Enter a Valid Resume");

        String fileName = resume.getOriginalFilename();
        Path dir = Paths.get("uploads/");
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
        Path path = dir.resolve(Objects.requireNonNull(resume.getOriginalFilename()));
        Files.write(path, resume.getBytes());

        Tika tika = new Tika();
        String extractedText = tika.parseToString(resume.getInputStream());

        String fileType = resume.getContentType();
        ResumeEntity resumeEntity = new ResumeEntity();
        resumeEntity.setFileName(fileName);
        resumeEntity.setFilePath("uploads/" + fileName + System.currentTimeMillis());
        resumeEntity.setFileType(fileType);
        resumeEntity.setResumeText(extractedText);
        resumeEntity.setUploadedAt(LocalDateTime.now());
        resumeEntity.setUserId(user.getId());
        resumeDao.save(resumeEntity);

    }
}
