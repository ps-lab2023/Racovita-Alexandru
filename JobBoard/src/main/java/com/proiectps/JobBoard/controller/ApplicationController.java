package com.proiectps.JobBoard.controller;

import com.proiectps.JobBoard.model.Application;
import com.proiectps.JobBoard.service.impl.ApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    @Autowired
    private ApplicationServiceImpl applicationService;

    @GetMapping
    public ResponseEntity<List<Application>> findAll() {
        return ResponseEntity.ok(applicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> findById(@PathVariable Long id) {
        Application application = applicationService.findById(id);
        return application != null ? ResponseEntity.ok(application) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Application> save(@RequestBody Application application) {
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

