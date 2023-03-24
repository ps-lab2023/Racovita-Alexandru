package com.proiectps.JobBoard.service.impl;

import com.proiectps.JobBoard.model.Company;
import com.proiectps.JobBoard.repository.CompanyRepository;
import com.proiectps.JobBoard.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return (List<Company>) companyRepository.findAll();
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    public Company findByName(String name) {
        return companyRepository.findByName(name);
    }

    // Implement additional business logic here
}
