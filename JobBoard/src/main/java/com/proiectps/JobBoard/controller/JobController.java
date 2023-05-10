package com.proiectps.JobBoard.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proiectps.JobBoard.model.Job;
import com.proiectps.JobBoard.model.User;
import com.proiectps.JobBoard.service.impl.JobServiceImpl;
import com.proiectps.JobBoard.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:4200/")
public class JobController {
    @Autowired
    private JobServiceImpl jobService;
    @Autowired
    private UserServiceImpl userService;
    @GetMapping("/findall")
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/findby/{id}")

    public ResponseEntity<Job> findById(@PathVariable Long id) {
        Job job = jobService.findById(id);
        return job != null ? ResponseEntity.ok(job) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Job> save(@RequestBody Job job) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.save(job));
    }
/*
    @PutMapping("/{id}")
    public ResponseEntity<Job> update(@PathVariable Long id, @RequestBody Job job) {
        if (!id.equals(job.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(jobService.save(job));
    }
*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    if (!userService.isCurrentUserAdmin()) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    jobService.deleteById(id);
    return ResponseEntity.ok().build();
}

    @PutMapping("/{id}")
    public ResponseEntity<Job> update(@PathVariable Long id, @RequestBody Job job) {
        /*if (!userService.isCurrentUserAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
*/
        if (!id.equals(job.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(jobService.save(job));
    }


    public boolean isAdmin(User user) {
        return user.getRole() == User.Role.ADMIN;
    }
/*
    @PostMapping("/bookmark/{userId}/{jobId}")
    public ResponseEntity<?> bookmarkJob(@PathVariable Long userId, @PathVariable Long jobId) {
        return ResponseEntity.ok(jobService.bookmarkJob(userId, jobId));
    }

  */  // Add additional endpoints here
}
