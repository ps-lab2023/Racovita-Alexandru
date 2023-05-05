package com.proiectps.JobBoard.controller;

import com.proiectps.JobBoard.model.Application;
import com.proiectps.JobBoard.model.Job;
import com.proiectps.JobBoard.model.User;
import com.proiectps.JobBoard.service.JobService;
import com.proiectps.JobBoard.service.UserService;
import com.proiectps.JobBoard.service.impl.ApplicationServiceImpl;
import com.proiectps.JobBoard.service.impl.JobServiceImpl;
import com.proiectps.JobBoard.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:4200/")
public class ApplicationController {
    private final ApplicationServiceImpl applicationService;
    private final UserServiceImpl userService;
    private final JobServiceImpl jobService;

    @Autowired
    public ApplicationController(ApplicationServiceImpl applicationService, UserServiceImpl userService, JobServiceImpl jobService) {
        this.applicationService = applicationService;
        this.userService = userService;
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Application>> findAll() {
        return ResponseEntity.ok(applicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> findById(@PathVariable Long id) {
        Application application = applicationService.findById(id);
        return application != null ? ResponseEntity.ok(application) : ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Application> save(@RequestParam("userId") Long userId,
                                            @RequestParam("jobId") Long jobId,
                                            @RequestParam("cvFile") MultipartFile cvFile) {
        User user = userService.findById(userId);
        Job job = jobService.findById(jobId);
        LocalDate applicationDate = LocalDate.now();

        byte[] cvBytes = null;
        if (cvFile != null) {
            try {
                cvBytes = cvFile.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Application application = Application.builder()
                .user(user)
                .job(job)
                .applicationDate(applicationDate)
                .cvpath("C:\\Users\\Home\\Desktop\\savedfiles"+cvFile.getOriginalFilename())
                .cvContentType(cvFile.getContentType())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(applicationService.save(application));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Application> update(@PathVariable Long id, @RequestBody Application application) {
        if (!id.equals(application.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(applicationService.save(application));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        applicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Add additional endpoints here
}
