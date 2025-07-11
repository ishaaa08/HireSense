package com.smartscreen.HireSense.controller;

import com.drew.lang.annotations.NotNull;
import com.smartscreen.HireSense.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.apache.tika.exception.TikaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ResumeController extends ApiRestController {

    private final ResumeService resumeService;

    @PostMapping("/upload-resume")
    public ResponseEntity<String> uploadResume(@RequestHeader("X-User-Email") String email, @RequestParam("resume") @NotNull MultipartFile resume) throws IOException, TikaException {

        resumeService.uploadResume(email, resume);
        return ResponseEntity.ok("Resume upload successfully");
    }
}
