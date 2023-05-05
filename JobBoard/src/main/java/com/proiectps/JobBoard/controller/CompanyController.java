package com.proiectps.JobBoard.controller;

import com.proiectps.JobBoard.model.Company;
import com.proiectps.JobBoard.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "http://localhost:4200/")
public class CompanyController {
    @Autowired
    private CompanyServiceImpl companyService;

    @GetMapping
    public ResponseEntity<List<Company>> findAll() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id) {
        Company company = companyService.findById(id);
        return company != null ? ResponseEntity.ok(company) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Company> save(@RequestBody Company company) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(company));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> update(@PathVariable Long id, @RequestBody Company company) {
        if (!id.equals(company.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(companyService.save(company));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        companyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Add additional endpoints here
}
