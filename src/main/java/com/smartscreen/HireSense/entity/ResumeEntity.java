package com.smartscreen.HireSense.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ResumeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long userId;
    private String fileName;
    private String filePath;
    private String fileType;
    @Column(name = "resume_text", columnDefinition = "TEXT")
    private String resumeText;
    private LocalDateTime uploadedAt;

}
