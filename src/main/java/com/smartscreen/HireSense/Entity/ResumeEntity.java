package com.smartscreen.HireSense.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ResumeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fileName;
    private String filePath;
    private String fileType;
    @Lob
    @Column(name = "resume_text", columnDefinition = "TEXT")
    private String resumeText;
    private LocalDateTime uploadedAt;

    @ManyToOne()
    @JoinColumn(name="user_id")
    UserEntity user;

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public String getResumeText() {
        return resumeText;
    }
    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }
    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }


}
