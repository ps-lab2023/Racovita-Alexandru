package com.proiectps.JobBoard;// FileUploader.java
import com.proiectps.JobBoard.model.Application;
import com.proiectps.JobBoard.model.Job;
import com.proiectps.JobBoard.model.User;
import com.proiectps.JobBoard.repository.JobRepository;
import com.proiectps.JobBoard.repository.UserRepository;
import com.proiectps.JobBoard.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class FileUploader {
    @Autowired
    private final ApplicationService applicationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    public FileUploader(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    public void write(MultipartFile file, Path dir) {
        Path filepath = Paths.get(dir.toString(), file.getOriginalFilename());

        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/savefile")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam("userId") Long userId,
                                                   @RequestParam("jobId") Long jobId
                                                   ) {
        String message;
        try {
            byte[] fileBytes = file.getBytes();
            write(file,Paths.get("C:\\Users\\Home\\Desktop\\savedfiles"));
            String contentType = file.getContentType();
            User user = userRepository.findById(userId).orElse(null);
            Job job = jobRepository.findById(jobId).orElse(null);
            if (user == null || job == null) {
                message = "User or Job not found";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }

            Application application = new Application();
            application.setUser(user);
            application.setJob(job);
            application.setApplicationDate(LocalDate.now());
            application.setCvPath("C:\\Users\\Home\\Desktop\\savedfiles" + "\\" + file.getOriginalFilename());
            application.setCvContentType(contentType);

            applicationService.save(application);

            message = "Successfully uploaded!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Failed to upload!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}
