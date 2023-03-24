package com.proiectps.JobBoard.repository;

import com.proiectps.JobBoard.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    Company findByName(String name);
}
