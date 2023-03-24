package com.proiectps.JobBoard.controller;

import com.proiectps.JobBoard.model.Job;
import com.proiectps.JobBoard.service.impl.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    @Autowired
    private JobServiceImpl jobService;

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/{id}")

    public ResponseEntity<Job> findById(@PathVariable Long id) {
        Job job = jobService.findById(id);
        return job != null ? ResponseEntity.ok(job) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Job> save(@RequestBody Job job) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.save(job));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> update(@PathVariable Long id, @RequestBody Job job) {
        if (!id.equals(job.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(jobService.save(job));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        jobService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Add additional endpoints here
}
