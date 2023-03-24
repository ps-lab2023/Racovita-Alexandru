package com.proiectps.JobBoard.service;

import com.proiectps.JobBoard.model.Company;
import com.proiectps.JobBoard.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CompanyService {

    public List<Company> findAll() ;

    public Company findById(Long id) ;
    public Company save(Company company) ;

    public void deleteById(Long id) ;

    public Company findByName(String name) ;

}
